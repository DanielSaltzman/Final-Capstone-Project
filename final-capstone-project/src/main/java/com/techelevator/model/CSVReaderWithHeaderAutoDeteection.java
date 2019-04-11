package com.techelevator.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class CSVReaderWithHeaderAutoDeteection {
	private static final String SAMPLE_CSV_FILE_PATH = "prototyping/sampleCSV.csv";

	public static void main(String[] args) throws IOException {

// Create a new buffered reader to read through CSV file    	
		BufferedReader bufferedReader = new BufferedReader(new FileReader(SAMPLE_CSV_FILE_PATH));
// pull the first three lines of the file and strip of excess data
		String surveyTitle = bufferedReader.readLine().replace(",,,,,,,,", "");
		String surveyDate = bufferedReader.readLine().replace(",,,,,,,,", "").replace("\"", "");
		String surveyRoom = bufferedReader.readLine().replace(",,,,,,,,", "").replace("Room: ", "");

		System.out.println(surveyTitle);
		System.out.println(surveyDate);
		System.out.println(surveyRoom);

// Skip the next three lines of the file, it doesn't contain anything useful and without skipping the lines 
// it will create blank entries 

		bufferedReader.readLine();
		bufferedReader.readLine();
		bufferedReader.readLine();

		CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT
				.withHeader("Presence", "Student Names", "Student Id", "The pace of yesterday's class was:",
						"The content of the previous class was:",
						"I feel my level of understanding of the previous day's material is:",
						"On a scale of 1-10, my energy level today is:")
				.withIgnoreHeaderCase().withTrim().withIgnoreEmptyLines());
	
		for (CSVRecord csvRecord : csvParser) {
			
			// Accessing values by Header names

			String presence = csvRecord.get("Presence");
			String studentNames = csvRecord.get("Student Names");
			String studentID = csvRecord.get("Student ID");
			String thePaceOfYesterdaysClass = csvRecord.get("The pace of yesterday's class was:");
			String theContentOfThePreviousClass = csvRecord.get("The content of the previous class was:");
			String understandingOfThePreviousDaysMaterial = csvRecord
					.get("I feel my level of understanding of the previous day's material is:");
			String energyLevel = csvRecord.get("On a scale of 1-10, my energy level today is:");

			System.out.println("Record No - " + csvRecord.getRecordNumber());
			System.out.println("---------------");
			System.out.println("Presence: " + presence);
			System.out.println("Student Names: " + studentNames);
			System.out.println("Student Id: " + studentID);
			System.out.println("The pace of yesterday's class was: " + thePaceOfYesterdaysClass);
			System.out.println("The content of the previous class was: " + theContentOfThePreviousClass);
			System.out.println("I feel my level of understanding of the previous day's material is : "
					+ understandingOfThePreviousDaysMaterial);
			System.out.println("On a scale of 1-10, my energy level today is: " + energyLevel);
			System.out.println("---------------\n\n");
		}

// Close both of these to avoid resource leaks 
		csvParser.close(); 
		bufferedReader.close(); 
	}

}
