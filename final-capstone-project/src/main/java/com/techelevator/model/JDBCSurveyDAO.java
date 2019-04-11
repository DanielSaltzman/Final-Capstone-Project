package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCSurveyDAO implements SurveyDAO {
	
	private JdbcTemplate jdbcTemplate;
	

    @Autowired
    public JDBCSurveyDAO(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }
    

	@Override
	public List<Survey> getAllSurveys() {
		
		String sql = "SELECT * FROM survey";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		
		List<Survey> surveys = new ArrayList<Survey>();
		while(result.next()) {
			surveys.add(mapRowToSurvey(result));
		}
		
		return surveys;
	}

	@Override
	public Survey getSurveyWithId(long id) {
		
		String sql = "SELECT survey_id, survey_date, survey_name, room, location, cohort_number, instructor, topic FROM survey WHERE survey_id = ? ; ";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
		
		Survey survey = mapRowToSurvey(result);
		
		return survey;
	}
	
	private Survey mapRowToSurvey(SqlRowSet result) {
		
		Survey survey = new Survey();
		
		survey.setSurveyId(result.getLong("survey_id"));
		survey.setCreatedDate(result.getString("survey_date"));
		survey.setSurveyName(result.getString("survey_name"));
		survey.setRoom(result.getString("room"));
		survey.setLocation(result.getString("location"));
		survey.setCohortNumber(result.getString("cohort_number"));
		survey.setInstructor(result.getString("instructor"));
		survey.setTopic(result.getString("topic"));
		
		return survey;
	}
	
}
