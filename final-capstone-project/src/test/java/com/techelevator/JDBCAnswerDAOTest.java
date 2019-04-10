package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.techelevator.model.Answer;
import com.techelevator.model.JDBCAnswerDAO;



public class JDBCAnswerDAOTest extends DAOIntegrationTest {

	@Autowired
	private JDBCAnswerDAO AnswerDAO;
	
	
	@Before
	public void setup() {
		AnswerDAO = new JDBCAnswerDAO(getDataSource());
	}
	
	@Test
	public void getAnswersBySurveyIdAndQuestionId_returns_the_correct_number_of_answers() {
		List<Answer> test = AnswerDAO.getAnswersBySurveyIdAndQuestionId(1, 1);
		
		
		Assert.assertEquals(2, test.size());
	}
}
