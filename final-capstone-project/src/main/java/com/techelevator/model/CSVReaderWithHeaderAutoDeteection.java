package com.techelevator.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVReaderWithHeaderAutoDeteection {


	public List<CsvData> getListOfCSVDataFromFile() throws IOException {
		
		List<CsvData> returnList = new ArrayList<CsvData>();
	; 
		
		 String SAMPLE_CSV_FILE_PATH = "prototyping/sampleCSV.csv";

			BufferedReader bufferedReader = new BufferedReader(new FileReader(SAMPLE_CSV_FILE_PATH));
			String surveyTitle = bufferedReader.readLine().replace(",,,,,,,,", "");
			String surveyDate = bufferedReader.readLine().replace(",,,,,,,,", "").replace("\"", "");
			String surveyRoom = bufferedReader.readLine().replace(",,,,,,,,", "").replace("Room: ", "");

			System.out.println(surveyTitle);
			System.out.println(surveyDate);
			System.out.println(surveyRoom);
			

			CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT
					.withHeader("Presence", "Student Names", "Student Id", "Total Score (0 - 100)", "Number of correct answers", "The pace of yesterday's class was:",
							"The content of the previous class was:",
							"I feel my level of understanding of the previous day's material is:",
							"On a scale of 1-10, my energy level today is:")
					.withIgnoreHeaderCase().withTrim().withIgnoreEmptyLines());
		
			
			for (CSVRecord csvRecord : csvParser) {
				CsvData csvLine = new CsvData(); 
				
				csvLine.setPresenceAnswer(csvRecord.get("Presence"));
				csvLine.setStudentName(csvRecord.get("Student Names"));
				csvLine.setStudentId("Student ID");
				csvLine.setPaceOfYesterdaysClassAnswer(csvRecord.get("The pace of yesterday's class was:"));
				csvLine.setContentOfPreviousClassAnswer(csvRecord.get("The content of the previous class was:"));
				csvLine.setUnderstandingOfPreviousDaysMaterialAnswer(csvRecord
						.get("I feel my level of understanding of the previous day's material is:"));
				csvLine.setEnergyLevel(csvRecord.get("On a scale of 1-10, my energy level today is:"));
			 
			}
			csvParser.close(); 
			bufferedReader.close(); 
		


		return returnList;
		
	}
}

	
//	private static final String SAMPLE_CSV_FILE_PATH = "prototyping/sampleCSV.csv";
//
//	public static void main(String[] args) throws IOException {
//
//// Create a new buffered reader to read through CSV file    	
//		BufferedReader bufferedReader = new BufferedReader(new FileReader(SAMPLE_CSV_FILE_PATH));
//// pull the first three lines of the file and strip of excess data
//		
//// These first three lines combined with the data that is submitted from the form (location, cohort #, instructor, topic)
//// when the CSV is uploaded Should be able to provide everything needed for the survey table
//		String surveyTitle = bufferedReader.readLine().replace(",,,,,,,,", "");
//		String surveyDate = bufferedReader.readLine().replace(",,,,,,,,", "").replace("\"", "");
//		String surveyRoom = bufferedReader.readLine().replace(",,,,,,,,", "").replace("Room: ", "");
//
//		System.out.println(surveyTitle);
//		System.out.println(surveyDate);
//		System.out.println(surveyRoom);
//
//// Skip the next three lines of the file, it doesn't contain anything useful and without skipping the lines 
//// it will create blank entries 
//
//		bufferedReader.readLine();
//		bufferedReader.readLine();
//		bufferedReader.readLine();
//
//// This will start parsing the CSV by the defined header listed below. 
//// I'm ignoring the total scores and number of correct answers columns because we aren't using them for anything. 
//		
//		CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT
//				.withHeader("Presence", "Student Names", "Student Id", "Total Score (0 - 100)", "Number of correct answers", "The pace of yesterday's class was:",
//						"The content of the previous class was:",
//						"I feel my level of understanding of the previous day's material is:",
//						"On a scale of 1-10, my energy level today is:")
//				.withIgnoreHeaderCase().withTrim().withIgnoreEmptyLines());
//	
//		for (CSVRecord csvRecord : csvParser) {
//			
//// Accessing values by getting the header names for each row on the table
//			
//			String presence = csvRecord.get("Presence");
//		
//			String studentNames = csvRecord.get("Student Names");
//			String studentID = csvRecord.get("Student ID");
//			String thePaceOfYesterdaysClass = csvRecord.get("The pace of yesterday's class was:");
//			String theContentOfThePreviousClass = csvRecord.get("The content of the previous class was:");
//			String understandingOfThePreviousDaysMaterial = csvRecord
//					.get("I feel my level of understanding of the previous day's material is:");
//			String energyLevel = csvRecord.get("On a scale of 1-10, my energy level today is:");
//
//			
//// This prints out all the records in a nicely formatted table so I could visualize what was happening. 
//// This is where we should start inserting these into objects or perhaps directly into the tables. I think that might be the best idea.
//			System.out.println("Record No - " + csvRecord.getRecordNumber());
//			System.out.println("---------------");
//			System.out.println("Presence: " + presence);
//			System.out.println("Student Names: " + studentNames);
//			System.out.println("Student Id: " + studentID);
//			System.out.println("The pace of yesterday's class was: " + thePaceOfYesterdaysClass);
//			System.out.println("The content of the previous class was: " + theContentOfThePreviousClass);
//			System.out.println("I feel my level of understanding of the previous day's material is : "
//					+ understandingOfThePreviousDaysMaterial);
//			System.out.println("On a scale of 1-10, my energy level today is: " + energyLevel);
//			System.out.println("---------------\n\n");
//		}
//
//// Close both of these to avoid resource leaks 
//		csvParser.close(); 
//		bufferedReader.close(); 
//	}
//
//}
