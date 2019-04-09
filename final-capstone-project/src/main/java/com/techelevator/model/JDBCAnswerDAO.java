package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCAnswerDAO implements AnswerDAO {
	
	private JdbcTemplate jdbcTemplate; 
	
	 @Autowired
	    public JDBCAnswerDAO (DataSource datasource) {
	        this.jdbcTemplate = new JdbcTemplate(datasource);
	    }

	@Override
	public List<Answer> getAnswersBySurveyIdAndQuestionId(int surveyId, int question_id) {
		String sql = "SELECT answer_id, student_id, answer_text from ANSWER where survey_id = ? AND question_id = ?" ; 
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, surveyId, question_id);
		
		List<Answer> answers = new ArrayList<Answer>();
		while(result.next()) {
			answers.add(mapRowToAnswer(result));
		}
		
		return answers;	
	}

	private Answer mapRowToAnswer(SqlRowSet result) {
		Answer answer = new Answer(); 
		
		answer.setAnswerId(result.getLong("answer_id"));
		answer.setStudentId(result.getLong("student_id"));
		answer.setAnswerText(result.getString("answer_text"));
		
		return answer; 
	}
	
	

}
