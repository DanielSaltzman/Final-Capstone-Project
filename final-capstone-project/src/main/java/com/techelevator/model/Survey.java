package com.techelevator.model;

import java.util.List;

public class Survey {
	
	private long surveyId; 
	private String surveyName;
	private String createdDate;
	private List<Question> surveyList;
	
	
	public long getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(long surveyId) {
		this.surveyId = surveyId;
	}
	public String getSurveyName() {
		return surveyName;
	}
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public List<Question> getSurveyList() {
		return surveyList;
	}
	public void setSurveyList(List<Question> surveyList) {
		this.surveyList = surveyList;
	}
	
}
