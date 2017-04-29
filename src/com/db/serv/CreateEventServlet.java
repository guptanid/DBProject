package com.db.serv;

import java.io.IOException;
import java.sql.Connection;
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
		String action = getParamIfAvailable(request, "action");
		String url = null;
		if(action == null) {
			url = "/listevent.jsp";
		} else {
			
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entered doGet");
		String eventId = getParamIfAvailable(req, "id");
		String act = getParamIfAvailable(req, "act");
		String url = null;
		if(eventId != null) {
			Connection connection = null;
			ConnectionPool pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			EventAccessor eventAccessor = new EventAccessor(connection);
			
			
			if(eventId.equalsIgnoreCase("all")) 
			{
				List<Event> events = eventAccessor.selectAllEvents();
				req.setAttribute("events", events);
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
			url = "/event.jsp";
		}
		if(url == null) {
			url = "/listevent.jsp";
		}
		
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String act = getParamIfAvailable(req, "act");
		if(act != null) {
			if(act.equalsIgnoreCase("delete")) {
				
			}
		}
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
}
