package com.techelevator.model;

import java.util.List;

public interface SurveyDAO {
	
	public List<Survey> getAllSurveys();
	
	public Survey getSurveyWithId(long id);
	
}
