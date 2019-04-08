package com.techelevator.model;

import java.util.Date;
import java.util.List;

public class Survey {
	
	private List<Question> surveyList;
	private long surveyId; 
	private Date surveyDate;
	
	
	public List<Question> getSurveyList() {
		return surveyList;
	}
	public void setSurveyList(List<Question> surveyList) {
		this.surveyList = surveyList;
	}
	public long getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(long surveyId) {
		this.surveyId = surveyId;
	}
	public Date getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}
	
}
