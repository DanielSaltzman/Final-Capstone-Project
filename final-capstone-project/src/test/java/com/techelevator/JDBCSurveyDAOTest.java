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
	
}
