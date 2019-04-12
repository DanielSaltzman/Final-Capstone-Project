package com.techelevator.model;

import java.util.List;

public interface AnswerDAO {

	public List<Answer> getAnswersBySurveyIdAndQuestionId(int surveyId, int question_id); 
	
	public void createNewAnswer(long questionId, String answerText, String studentId, long surveyId); 
}
