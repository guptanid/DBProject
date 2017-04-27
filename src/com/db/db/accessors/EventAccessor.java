package com.db.db.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;

import com.db.db.objects.Event;
import com.db.db.util.Constants;
import com.db.db.util.DbUtil;

public class EventAccessor {

	private Connection connection = null;

	public EventAccessor(Connection connection) {
		this.connection = connection;
	}

	public List<Event> selectAllEvents() {
		CallableStatement cs = null;
		List<Event> events = new ArrayList<Event>();
		ResultSet rs = null;
		try {
			cs = this.connection.prepareCall("{CALL GetAllEvents()}");
			rs = cs.executeQuery();
			while (rs.next()) {
				Event event = asEvent(rs);
				if(event != null) {
					events.add(event);
				}
			}
			return events;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DbUtil.closeResultSet(rs);
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Event selectEvent(int id) {
		ResultSet rs = null;
		CallableStatement cs = null;
		try {
			cs = this.connection.prepareCall("{CALL GetEventDetails(?)}");
			cs.setInt(1, id);
			rs = cs.executeQuery();
			while(rs.next()) {
				Event event = asEvent(rs);
				return event;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.closePreparedStatement(ps);
		}
	}

	private Event asEvent(ResultSet rs) {
		Event event = new Event();
		try {
			event.setId(rs.getInt(Constants.EVENT_ID));
			event.setCategoryId(rs.getInt(Constants.CATEGORY_ID));
			event.setDepartmentId(rs.getInt(Constants.DEPARTMENT_ID));
			event.setDescription(rs.getString(Constants.EVENT_DESCRIPTION));
			event.setEndDate(rs.getDate(Constants.END_DATE));
			event.setEndTime(rs.getTime(Constants.END_TIME));
			event.setLocation(rs.getString(Constants.LOCATION));
			event.setName(rs.getString(Constants.EVENT_NAME));
			event.setStartDate(rs.getDate(Constants.START_DATE));
			event.setStartTime(rs.getTime(Constants.START_TIME));
			return event;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
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
