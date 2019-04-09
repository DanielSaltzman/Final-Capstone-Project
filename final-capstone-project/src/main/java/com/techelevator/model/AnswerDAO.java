package com.techelevator.model;

import java.util.List;

public interface AnswerDAO {

	public List<Answer> getAnswersBySurveyIdAndQuestionId(int surveyId, int question_id); 
	

}
