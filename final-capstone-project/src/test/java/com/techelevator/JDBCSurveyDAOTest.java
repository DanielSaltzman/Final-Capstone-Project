package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.techelevator.model.JDBCSurveyDAO;
import com.techelevator.model.Survey;

public class JDBCSurveyDAOTest extends DAOIntegrationTest {
	
	@Autowired
	private JDBCSurveyDAO SurveyDAO;
	
	@Before
	public void setup() {
		SurveyDAO = new JDBCSurveyDAO(getDataSource());
	}
	
	@Test
	public void getAllSurveys_returns_a_populated_list() {
		List<Survey> testList = SurveyDAO.getAllSurveys();
		
		Assert.assertNotEquals(0, testList.size());
	}
	
	@Test
	public void getSurveyWithId_returns_the_correct_survey() {
		Survey survey = SurveyDAO.getSurveyWithId(1);
		
		Assert.assertEquals("Wednesday, May 23 2018 09:02 AM", survey.getCreatedDate());
		Assert.assertEquals("survey one", survey.getSurveyName());
		Assert.assertEquals("tecbusjavab", survey.getRoom());
		Assert.assertEquals("Columbus", survey.getCampus());
		Assert.assertEquals("7", survey.getCohortNumber());
		Assert.assertEquals("Brian Lauvray", survey.getInstructor());
		Assert.assertEquals("Magnets: How do they work?", survey.getTopic());
	}
	
}
