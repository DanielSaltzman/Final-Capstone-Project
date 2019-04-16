package com.techelevator.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Answer;
import com.techelevator.model.AnswerDAO;
import com.techelevator.model.AnswerStats;
import com.techelevator.model.AnswerStatsDAO;
import com.techelevator.model.CsvData;
import com.techelevator.model.CsvParser;
import com.techelevator.model.Log;
import com.techelevator.model.LogDAO;
import com.techelevator.model.Question;
import com.techelevator.model.QuestionDAO;
import com.techelevator.model.StudentDAO;
import com.techelevator.model.Survey;
import com.techelevator.model.SurveyDAO;
import com.techelevator.model.SurveyQuestionDAO;
import com.techelevator.model.SurveySubmission;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
public class AuthenticationController {

	private UserDAO userDAO;
	private CsvParser csvParser = new CsvParser(); 

	@Autowired
	public AuthenticationController(UserDAO userDAO) {
		this.userDAO = userDAO; 
	}
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private SurveyDAO surveyDao;
	
	@Autowired
	private AnswerDAO answerDao;
	
	@Autowired
	private QuestionDAO questionDao;
	
	@Autowired
	private StudentDAO studentDao; 
	
	@Autowired
	private LogDAO logDao;
	
	@Autowired
	private AnswerStatsDAO answerStatsDao; 
	
	
	@Autowired
	private SurveyQuestionDAO surveyQuestionDao; 
	
	@Value("${path}")
	private String path;

	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String displayLoginForm() {
		return "login";
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.GET)
	public String displaySurveyView(ModelMap map, HttpSession session) {
		
		if(session.getAttribute("currentUser") != null) {
			List<Survey> surveyList = surveyDao.getAllSurveys();
			
			map.addAttribute("surveys", surveyList);
			
			return "survey";
		} else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(path="/deleteSurvey", method=RequestMethod.POST) 
	public String deleteSurvey(@RequestParam long id, HttpSession session) {
		
		surveyDao.deleteExistingSurvey(id);
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.inserLog(user.getUserName(), "User Deleted Survey " + id);
		
		return "redirect:/survey";
	}
	
	@RequestMapping(path="/editSurvey", method=RequestMethod.POST) 
	public String editSurvey(@RequestParam long id, @RequestParam String campus, @RequestParam String cohortNumber, @RequestParam String instructor, @RequestParam String topic, HttpSession session) {
		
		surveyDao.updateSurvey(id, campus, cohortNumber, instructor, topic);
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.inserLog(user.getUserName(), "User Edited Survey " + id);
		
		return "redirect:/surveyDetails?surveyId=" + id;
	}
	
	@RequestMapping(path="/surveyDetails", method=RequestMethod.GET)
	public String displaySurveyDetailView(ModelMap map, @RequestParam long surveyId, HttpSession session) {
		
		if(session.getAttribute("currentUser") != null) {
			List<Survey> surveyList = surveyDao.getAllSurveys();
			
			for(Survey survey : surveyList) {
				if(survey.getSurveyId() == surveyId) {
					map.addAttribute("selectedSurvey", survey);
				}
			}
			
			List<Question> questionList = questionDao.getQuestionsBySurveyId(surveyId);
			List<AnswerStats> surveyStats = answerStatsDao.getCountAndTextOfUniqueAnswersForSurvey(surveyId); 
			
			map.addAttribute("questions", questionList);
			map.addAttribute("surveyStats", surveyStats); 
			

			return "surveyDetails";
		} else {
			return "redirect:/login";
		}
		
	}
	
	@RequestMapping(path="/answers", method=RequestMethod.GET)
	public String displayAnswerDetailView(ModelMap map, @RequestParam long surveyId, @RequestParam long questionId, HttpSession session) {
		
		if(session.getAttribute("currentUser") != null) {
			
			List<Answer> answerList = answerDao.getStudentNameAndAnswerBySurveyIdAndQuestionId(surveyId, questionId);
			
			List<Question> questionList = questionDao.getQuestionsBySurveyId(surveyId);
			
// get statistics 
			
			List<AnswerStats> surveyQuestionStats = answerStatsDao.getCountAndTextOfUniqueAnswersForSurveyQuestion(questionId, surveyId); 
			
			for(Question question : questionList) {
				if(question.getQuestionId() == questionId) {
					map.addAttribute("selectedQuestion", question);
				}
			}
			
			map.addAttribute("answers", answerList);

//add statistics to model map
			
			map.addAttribute("surveyQuestionStats", surveyQuestionStats); 

// Get truncated answer text for the bar graph
			
			for(AnswerStats stats : surveyQuestionStats) {
				if(stats.getAnswerText().length() > 25) {
					stats.setAnswerText(stats.getAnswerText().substring(0,23) + "...");
				}
				map.addAttribute("truncatedStats", surveyQuestionStats);
			}
			
			return "answers";
			
		} else {
			return "redirect:/login";
		}
		
	}
	
	@RequestMapping(path="/uploadFile", method=RequestMethod.POST)
	public String handleFileUpload(SurveySubmission submission , ModelMap map, HttpSession session) throws IOException {
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.inserLog(user.getUserName(), "Uploaded Survey");
		
		
		File csvPath = getCSVFilePath();
		String csvName = csvPath + File.separator + "csvFile";
		
		createCSV(submission.getFile(), csvName);

// grab the file path and the submission object and pass it into the database writer		
		List<CsvData> csvData = csvParser.getListOfCSVDataFromFile(csvName);  
// get the survey id before you insert it
		surveyDao.createNewSurvey(csvData.get(0).getSurveyDate(), csvData.get(0).getSurveyTitle(), csvData.get(0).getSurveyRoom(), submission.getLocation(), submission.getCohortNumber(), submission.getInstructor(), submission.getTopic());
		long surveyId = surveyDao.getNextSurveyId() -1; 
		long question1Id = questionDao.getQuestionIdByQuestionText(csvData.get(0).getQuestion1()); 
		long question2Id = questionDao.getQuestionIdByQuestionText(csvData.get(0).getQuestion2()); 
		long question3Id = questionDao.getQuestionIdByQuestionText(csvData.get(0).getQuestion3());  
		long question4Id = questionDao.getQuestionIdByQuestionText(csvData.get(0).getQuestion4()); 
		long question5Id = questionDao.getQuestionIdByQuestionText(csvData.get(0).getQuestion5());  

// insert each answer on the csv into answer line by line	
		for (CsvData eachLine : csvData) {
			answerDao.createNewAnswer(question1Id, eachLine.getPresenceAnswer(), eachLine.getStudentId(), surveyId);
			answerDao.createNewAnswer(question2Id, eachLine.getPaceOfYesterdaysClassAnswer(), eachLine.getStudentId(), surveyId);
			answerDao.createNewAnswer(question3Id, eachLine.getContentOfPreviousClassAnswer(), eachLine.getStudentId(), surveyId);
			answerDao.createNewAnswer(question4Id, eachLine.getUnderstandingOfPreviousDaysMaterialAnswer(), eachLine.getStudentId(), surveyId);
			answerDao.createNewAnswer(question5Id, eachLine.getEnergyLevel(), eachLine.getStudentId(), surveyId);
			studentDao.insertStudentIntoTableIfStudentDoesNotExits( eachLine.getStudentId(), eachLine.getStudentName());
			
		}
// insert each question on the survey into survey_question		
		surveyQuestionDao.createNewRow(question1Id, surveyId);
		surveyQuestionDao.createNewRow(question2Id, surveyId);
		surveyQuestionDao.createNewRow(question3Id, surveyId);
		surveyQuestionDao.createNewRow(question4Id, surveyId);
		surveyQuestionDao.createNewRow(question5Id, surveyId);
		

		
		return "redirect:/survey";
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST) 
	public String login(@RequestParam String userName, 
						@RequestParam String password, 
						HttpSession session) {
		
		if(userDAO.searchForUsernameAndPassword(userName, password)) {
			session.setAttribute("currentUser", userDAO.getUserByUserName(userName));
			User user = ((User) session.getAttribute("currentUser"));
			
			if(userDAO.isTemporaryPassword(user.getUserNameId())) {
				return "redirect:/changeOneTimePassword";
			}
			
			logDao.inserLog(user.getUserName(), "Successful Login");
			return "redirect:/survey";
		} else {
			logDao.inserLog("Unknown", "Failed Login");
			return "redirect:/login";
		}
	}
	
	@RequestMapping(path="/changePassword", method=RequestMethod.POST) 
	public String changePassword(@RequestParam String userName, @RequestParam String password, HttpSession session, ModelMap model) {
		
		userDAO.updatePassword(userName, password);
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.inserLog(user.getUserName(), "User Changed Password");
		
		model.remove("currentUser");
		session.invalidate();
		
		return "redirect:/login";
		
	}
	
	@RequestMapping(path="/changeOneTimePassword", method=RequestMethod.POST) 
	public String changeOneTimePassword(@RequestParam String userName, @RequestParam String password, HttpSession session, ModelMap model) {
		
		userDAO.updatePassword(userName, password);
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.inserLog(user.getUserName(), "User Changed One-Time Password");
		
		
		return "redirect:/survey";
		
	}
	
	@RequestMapping(path="/changeOneTimePassword", method=RequestMethod.GET)
	public String displayChangeOneTimePasswordView(ModelMap map, HttpSession session) {
		
		if(((User) session.getAttribute("currentUser")) != null) {
			
			return "changeOneTimePassword";
		}
		
		return "redirect:/login";
	}
	
	@RequestMapping(path="/setOneTimePassword", method=RequestMethod.POST) 
	public String setOneTimePassword(@RequestParam long userNameId, @RequestParam String password, HttpSession session) {
		
		userDAO.updatePasswordTemporary(userNameId, password);
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.inserLog(user.getUserName(), "Admin Set One-Time Password");
		
		
		return "redirect:/userView";
		
	}
	

	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String logout(ModelMap model, HttpSession session) {
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.inserLog(user.getUserName(), "User Logged Out");
		
		model.remove("currentUser");
		session.invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping(path="/userView", method=RequestMethod.POST)
	public String addUser(@RequestParam String userName, @RequestParam String password, @RequestParam String role, HttpSession session) {
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.inserLog(user.getUserName(), "Admin Added New User");
		
		userDAO.saveUser(userName, password, role);
		
		return "redirect:/userView";
	}
	
	@RequestMapping(path="/deleteUser", method=RequestMethod.POST)
	public String addUser(@RequestParam long id, HttpSession session) {
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.inserLog(user.getUserName(), "Admin Deleted User");
		
		userDAO.deleteUser(id);
		
		return "redirect:/userView";
	}
	
	@RequestMapping(path="/editUser", method=RequestMethod.POST)
	public String editUser(@RequestParam long id, @RequestParam String role, HttpSession session) {
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.inserLog(user.getUserName(), "Admin Changed User Role");
		
		if(role.equals("Admin")) {
			userDAO.updateRole("User", id);
		} else {
			userDAO.updateRole("Admin", id);
		}
		
		return "redirect:/userView";
	}
	
	@RequestMapping(path="/userView", method=RequestMethod.GET)
	public String displayUserView(ModelMap map, HttpSession session) {
		
		if(((User) session.getAttribute("currentUser")).getRole().equals("Admin")) {
			
			List<User> userList = userDAO.getAllUsers();
			
			map.addAttribute("users", userList);
			
			return "userView";
		}
		
		return "redirect:/login";
	}
	
	@RequestMapping(path="/log", method=RequestMethod.GET)
	public String displayLogView(ModelMap map, HttpSession session) {
		
		User user = (User) session.getAttribute("currentUser");
		
		if(((User) session.getAttribute("currentUser")).getRole().equals("Admin")) {
			
			List<Log> logList = logDao.getAllLogs();
		
			map.addAttribute("logs", logList);
			
			return "log";
		}
		
		return "redirect:/login";
	}
	
	private File getCSVFilePath() {
		String serverPath = getServerContextPath();
		File filePath = new File(serverPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		return filePath;
	}
	
	private String getServerContextPath() {
		return servletContext.getRealPath("/") + path; //"uploads";
	}
	
	private void createCSV(MultipartFile file, String name) {
		File image = new File(name);
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image))) {
	
			stream.write(file.getBytes());
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}