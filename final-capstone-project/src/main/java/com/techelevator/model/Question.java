package com.techelevator.model;

public class Question {
	
	private long questionId;
	private long surveyId;
	private int questionNumber;
	private String questionText;
	private String questionAnwser;

	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public long getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(long surveyId) {
		this.surveyId = surveyId;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getQuestionAnwser() {
		return questionAnwser;
	}
	public void setQuestionAnwser(String questionAnwser) {
		this.questionAnwser = questionAnwser;
	}
	
}
