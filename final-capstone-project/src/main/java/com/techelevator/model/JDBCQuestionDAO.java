package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCQuestionDAO implements QuestionDAO {
	
	private JdbcTemplate jdbcTemplate; 
	
    @Autowired
    public JDBCQuestionDAO(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public List<Question> getQuestionsBySurveyId(int id) {
    	String sql = "SELECT question.question_id, question.question_text from survey_question join question on question.question_id = survey_question.question_id where survey_id = ?"; 
    	SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

    	List<Question> questions = new ArrayList<Question>();
    	while(result.next()) {
    		questions.add(mapRowToQuestion(result));
    	}
    	return questions;
    }
	
	
private Question mapRowToQuestion(SqlRowSet result) {
		
		Question question = new Question();
		question.setQuestionId(result.getLong("question.question_id")); 
		question.setQuestionText(result.getString("question.question_text"));  

		return question;
	}



}
