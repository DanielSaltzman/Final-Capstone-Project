package com.techelevator.model;

public interface StudentDAO {

	public void insertStudentIntoTableIfStudentDoesNotExits(String studentId, String studentName);

}
