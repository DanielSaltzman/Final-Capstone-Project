package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.JDBCQuestionDAO;
import com.techelevator.model.JDBCSurveyDAO;
import com.techelevator.model.Question;



public class JDBCQuestionDAOTest extends DAOIntegrationTest {
	
	@Autowired
	private JDBCQuestionDAO QuestionDAO;
	private JdbcTemplate jdbcTemplate;
	Integer id;
	Integer questionId;
	
	@Before
	public void setup() {
		
			String truncateSql = "TRUNCATE survey_question, survey, question";
			String sqlInsertQuestion = "INSERT INTO question(question_text) VALUES ('test question')";
			String sqlInsertSurvey = "INSERT INTO survey(survey_date, survey_name, room, campus, cohort_number, instructor, topic) VALUES ('Wednesday, May 23 2018 09:02 AM', 'survey one', 'tecbusjavab', 'Columbus', '7',  'Brian Lauvray', 'Magnets: How do they work?') Returning survey_id";
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
			jdbcTemplate.update(truncateSql);
			jdbcTemplate.update(sqlInsertQuestion);
			id = jdbcTemplate.queryForObject(sqlInsertSurvey, Integer.class);
			
	
		
		QuestionDAO = new JDBCQuestionDAO(getDataSource());
	}
	
	@Test
	public void getQuestionsBySurveyId_returns_the_correct_amount(){
		
		List<Question> testList = QuestionDAO.getQuestionsBySurveyId(id);
		
		Assert.assertEquals(1, testList.size());
	}
	

}
