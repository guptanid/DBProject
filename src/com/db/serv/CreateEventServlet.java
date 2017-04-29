package com.db.serv;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.db.accessors.EventAccessor;
import com.db.db.objects.Category;
import com.db.db.objects.Department;
import com.db.db.objects.Event;
import com.db.db.util.ConnectionPool;
import com.db.db.util.DbUtil;

/**
 * Servlet implementation class CreateEventServlet
 */
//@WebServlet("/event")
public class CreateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entered doPost");
		String action = getParamIfAvailable(request, "action");
		System.out.println(action);
		
		String url = null;
		if(action == null) {
			url = "/listevent.jsp";
			loadEventList(request);
		} else {
			if(action.equalsIgnoreCase("update")) {
				updateEvent(request);
			}
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entered doGet");
		String act = getParamIfAvailable(req, "act");
		String eventId = getParamIfAvailable(req, "id");
		EventAccessor eventAccessor = null;
		Connection connection = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		eventAccessor = new EventAccessor(connection);
		
		String url = null;
		if(eventId != null) {
			
			
			if(eventId.equalsIgnoreCase("all")) 
			{
				loadEventList(req, eventAccessor);
				url = "/listevent.jsp";
			} else 
			{
				int id = tryParseInt(eventId);
				System.out.println("Getting event " + id);
				if(id != -1) 
				{
					Event event = eventAccessor.selectEvent(id);
					req.setAttribute("event", event);
					
					if(act == null)
					{
						url = "/eventdetails.jsp";
					} else if(act.equalsIgnoreCase("edit"))
					{
						url = "/updateevent.jsp";
						loadCategories(req, eventAccessor);
						loadDepartments(req, eventAccessor);
					}
				}
			}
			pool.freeConnection(connection);
		} else {
			if(act != null && "add".equalsIgnoreCase(act)) {
				loadCategories(req, eventAccessor);
				loadDepartments(req, eventAccessor);
				url = "/event.jsp";
			} else {
				url = "/listevent.jsp";
				loadEventList(req, eventAccessor);
			}
		}
		if(url == null) {
			url = "/listevent.jsp";
		}
		
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}

	private String getParamIfAvailable(HttpServletRequest req, String param) {
		try {
			String value = req.getParameter(param);
			return value;
		} catch (Exception e) {
			return null;
		}
	}
	
	private int tryParseInt(String arg) {
		try {
			int newInt = Integer.parseInt(arg);
			return newInt;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	private void loadCategories(HttpServletRequest req, EventAccessor eventAccessor) {
		List<Category> categories = null;
		categories = eventAccessor.selectAllCategories();
		req.setAttribute("categories", categories);
	}
	
	private void loadDepartments(HttpServletRequest req, EventAccessor eventAccessor) {
		List<Department> departments = null;
		departments = eventAccessor.selectAllDepartments();
		req.setAttribute("departments", departments);
	}
	
	private void loadEventList(HttpServletRequest req) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		EventAccessor eventAccessor = new EventAccessor(connection);
		loadEventList(req, eventAccessor);
		pool.freeConnection(connection);
	}
	
	private void loadEventList(HttpServletRequest req, EventAccessor eventAccessor) {
		List<Event> events = eventAccessor.selectAllEvents();
		req.setAttribute("events", events);
	}
	
	private void updateEvent(HttpServletRequest req) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		EventAccessor eventAccessor = new EventAccessor(connection);
		
		Event event = new Event();
		String category = getParamIfAvailable(req, "category");
		String eventIdString = getParamIfAvailable(req, "id");
		int eventId = tryParseInt(eventIdString);
		event.setId(eventId);
		int intCategory = tryParseInt(category);
		event.setCategoryId(intCategory);
		
		String departmentId = getParamIfAvailable(req, "department");
		int intDepartment = tryParseInt(departmentId);
		event.setDepartmentId(intDepartment);
		
		String eventStartDate = getParamIfAvailable(req, "eventStartDate");
		event.setStartDate(parseDate(eventStartDate));
		
		String eventStartTime = getParamIfAvailable(req, "eventStartTime");
		event.setStartTime(parseTime(eventStartTime));
		
		String location = getParamIfAvailable(req, "eventLocation");
		event.setLocation(location);
		
		String description = getParamIfAvailable(req, "eventName");
		event.setDescription(description);
		
		
		eventAccessor.updateEvent(event);
	}
	
	@SuppressWarnings("deprecation")
	private Date parseDate(String dateString) {
		String[] parseArray = dateString.split("-");
		int year, month, day;
		year = Integer.parseInt(parseArray[0]);
		month = Integer.parseInt(parseArray[1]);
		day = Integer.parseInt(parseArray[2]);
		return new Date(year, month, day);
	}
	
	@SuppressWarnings("deprecation")
	private Time parseTime(String timeString) {
		String[] parseArray = timeString.split(":");
		int hour, minute, second;
		hour = Integer.parseInt(parseArray[0]);
		minute = Integer.parseInt(parseArray[1]);
		second = Integer.parseInt(parseArray[2]);
		return new Time(hour, minute, second);
	}
}
