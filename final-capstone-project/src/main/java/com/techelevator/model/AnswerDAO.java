package com.techelevator.model;

import java.util.List;

public interface AnswerDAO {

	public List<Answer> getAnswersBySurveyIdAndQuestionId(long surveyId, long questionId); 
	
	public void createNewAnswer(long questionId, String answerText, String studentId, long surveyId);
	
	public List<Answer> getStudentNameAndAnswerBySurveyIdAndQuestionId (long surveyId, long questionId); 
}
