package com.techelevator.model;

import java.util.List;

public interface LogDAO {
	
	public List<Log> getAllLogs(); 


	void inserLog(String editingUser, String logText); 
}
