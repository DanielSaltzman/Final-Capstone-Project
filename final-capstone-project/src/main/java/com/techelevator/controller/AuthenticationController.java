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

import com.techelevator.model.CsvData;
import com.techelevator.model.CsvParser;
import com.techelevator.model.Question;
import com.techelevator.model.QuestionDAO;
import com.techelevator.model.Survey;
import com.techelevator.model.SurveyDAO;
import com.techelevator.model.SurveySubmission;
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
	private QuestionDAO questionDao;
	
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
			
			map.addAttribute("questions", questionList);
			
			return "surveyDetails";
		} else {
			return "redirect:/login";
		}
		
	}
	
	@RequestMapping(path="/uploadFile", method=RequestMethod.POST)
	public String handleFileUpload(SurveySubmission submission , ModelMap map) throws IOException {
		
		File csvPath = getCSVFilePath();
		String csvName = csvPath + File.separator + "csvFile";
		
		createCSV(submission.getFile(), csvName);

// grab the file path and the submission object and pass it into the database writer		
		List<CsvData> csvData = csvParser.getListOfCSVDataFromFile(csvName);  
// get the survey id before you insert it
//		long surveyId = surveyDao.getNextSurveyId(); 
		surveyDao.createNewSurvey(csvData.get(0).getSurveyDate(), csvData.get(0).getSurveyTitle(), csvData.get(0).getSurveyRoom(), submission.getLocation(), submission.getCohortNumber(), submission.getInstructor(), submission.getTopic());
		
		return "login";
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public String login(@RequestParam String userName, 
						@RequestParam String password, 
						HttpSession session) {
		if(userDAO.searchForUsernameAndPassword(userName, password)) {
			session.setAttribute("currentUser", userDAO.getUserByUserName(userName));
			return "redirect:/survey";
		} else {
			return "redirect:/login";
		}
	}
	

	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String logout(ModelMap model, HttpSession session) {
		model.remove("currentUser");
		session.invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping(path="/userView", method=RequestMethod.POST)
	public String addUser(@RequestParam String userName, @RequestParam String password, @RequestParam String role) {
		
		userDAO.saveUser(userName, password, role);
		
		return "userView";
	}
	
	@RequestMapping(path="/userView", method=RequestMethod.GET)
	public String displayUserView() {
		return "userView";
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