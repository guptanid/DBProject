package com.db.db.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.db.db.objects.Event;
import com.db.db.util.Constants;
import com.db.db.util.DbUtil;

public class EventAccessor {

	private Connection connection = null;
	
	public EventAccessor(Connection connection) {
		this.connection = connection;
	}
	
	public void addEvent(Event event) {
		PreparedStatement ps = null;
		try {
			ps = getInsertEventStatement();
			ps.setInt(1, event.getDepartmentId());
			ps.setInt(2, event.getCategoryId());
			ps.setString(3, event.getLocation());
			ps.setDate(4, event.getStartDate());
			ps.setDate(5, event.getEndDate());
			ps.setTime(6, event.getStartTime());
			ps.setTime(7, event.getEndTime());
			
			ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} finally {
			DbUtil.closePreparedStatement(ps);
		}
	}
	
	private PreparedStatement getInsertEventStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(Constants.EVENTS);
		sb.append(" ( ");
		sb.append(Constants.DEPARTMENT_ID);
		sb.append(", ");
		sb.append(Constants.CATEGORY_ID);
		sb.append(", ");
		sb.append(Constants.LOCATION);
		sb.append(", ");
		sb.append(Constants.START_TIME);
		sb.append(", ");
		sb.append(Constants.END_DATE);
		sb.append(", ");
		sb.append(Constants.START_TIME);
		sb.append(", ");
		sb.append(Constants.END_TIME);
		sb.append(" ) VALUES ( ?, ?, ?, ?, ?, ?, ?");
		
		PreparedStatement ps = this.connection.prepareStatement(sb.toString());
		return ps;
	}
}
