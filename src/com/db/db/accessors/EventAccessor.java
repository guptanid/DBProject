package com.db.db.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;

import com.db.db.objects.Category;
import com.db.db.objects.Department;
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
	
	public void updateEvent(Event event) {
		/*
		 * UpdateEventDetails( IN p_event_id int,
IN p_department_id int, In p_category_id int,in p_location varchar(100),in p_description varchar(100), 
in p_startdate date, in  p_enddate date,in p_starttime time, in p_endtime time)
		 */
		
		CallableStatement cs = null;
		try {
			cs = this.connection.prepareCall(
					"{CALL UpdateEventDetails(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			cs.setInt(1, event.getId());
			cs.setInt(2, event.getDepartmentId());
			cs.setInt(3, event.getCategoryId());
			cs.setString(4, event.getLocation());
			cs.setString(5, event.getDescription());
			cs.setDate(6, event.getStartDate());
			cs.setDate(7, event.getEndDate());
			cs.setTime(8, event.getStartTime());
			cs.setTime(9, event.getEndTime());
			
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				System.out.println("Error closing Callable Statement");
			}
		}
	}
	
	public void deleteEvent(Event event) {
		CallableStatement cs = null;
		try {
			cs = this.connection.prepareCall("{CALL DeleteEventDetails(?)}");
			cs.setInt(1, event.getId());
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Category> selectAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getSelectCategoriesStatement();
			rs = ps.executeQuery();
			while(rs.next()) {
				categories.add(asCategory(rs));
			}
			
			return categories;
		} catch (SQLException e) {
			System.out.println("Error Loading Categories");
			return null;
		} finally {
			DbUtil.closePreparedStatement(ps);
			DbUtil.closeResultSet(rs);
		}
	}
	
	public List<Department> selectAllDepartments() {
		List<Department> departments = new ArrayList<Department>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getSelectDepartmentsStatement();
			rs = ps.executeQuery();
			while(rs.next()) {
				departments.add(asDepartment(rs));
			}
			return departments;
		} catch (SQLException e) {
			System.out.println("Error Loading Departments");
			e.printStackTrace();
			return null;
		}
	}

	private Event asEvent(ResultSet rs) {
		Event event = new Event();
		try {
			event.setId(rs.getInt(Constants.EVENT_ID));
			event.setCategory(rs.getString(Constants.CATEGORY_TYPE));
			event.setDepartment(rs.getString(Constants.DEPARTMENT_NAME));
			event.setDescription(rs.getString(Constants.EVENT_DESCRIPTION));
			event.setEndDate(rs.getDate(Constants.END_DATE));
			event.setEndTime(rs.getTime(Constants.END_TIME));
			event.setLocation(rs.getString(Constants.LOCATION));
			event.setStartDate(rs.getDate(Constants.START_DATE));
			event.setStartTime(rs.getTime(Constants.START_TIME));
			return event;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Category asCategory(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setId(rs.getInt(Constants.CATEGORY_ID));
		category.setName(rs.getString(Constants.CATEGORY_TYPE));
		return category;
	}
	
	private Department asDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt(Constants.DEPARTMENT_ID));
		department.setName(rs.getString(Constants.DEPARTMENT_NAME));
		return department;
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
	
	private PreparedStatement getSelectCategoriesStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM CATEGORY");
		
		PreparedStatement ps = this.connection.prepareStatement(sb.toString());
		return ps;
	}
	
	private PreparedStatement getSelectDepartmentsStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM DEPARTMENT");
		
		PreparedStatement ps = this.connection.prepareStatement(sb.toString());
		return ps;
	}
}
