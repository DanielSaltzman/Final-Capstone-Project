package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCLogDAO implements LogDAO {
	
	private JdbcTemplate jdbcTemplate; 
	
    @Autowired
    public JDBCLogDAO(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

	@Override
	public List<Log> getAllLogs() {
		String sql = "Select log_event, editing_user, log_text, event_date from Log"; 
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql); 
		
		List<Log> logs = new ArrayList<Log>(); 
		while(result.next()) {
			logs.add(mapRowToLog(result)); 
		}
		
		return logs;
	}
	
	private Log mapRowToLog(SqlRowSet result) {
		Log log = new Log(); 
		log.setLogEvent(result.getLong("log_event"));
		log.setEditingUser(result.getString("editing_user"));
		log.setLogText(result.getString("log_text")); 
		log.setTimestamp(result.getDate("event_date").toString());
		
		
		return log; 
	}

}
