package com.techelevator.model;

import java.util.List;

public interface LogDAO {
	
	public List<Log> getAllLogs(); 

	public void inserLog(Log logToInsert); 
}
