package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.techelevator.model.JDBCAnswerDAO;
import com.techelevator.model.JDBCQuestionDAO;
import com.techelevator.model.Question;



public class JDBCQuestionDAOTest extends DAOIntegrationTest {
	
	@Autowired
	private JDBCQuestionDAO QuestionDAO;
	
	@Before
	public void setup() {
		QuestionDAO = new JDBCQuestionDAO(getDataSource());
	}
	
	@Test
	public void getQuestionsBySurveyId_returns_the_correct_amount(){
		List<Question> testList = QuestionDAO.getQuestionsBySurveyId(1);
		
		Assert.assertEquals(5, testList.size());
	}

}
