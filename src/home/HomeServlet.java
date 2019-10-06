package home;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import database.ReadQuery;

/**
 * Servlet implementation class HomeServlet for the home page
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	ReadQuery rq = new ReadQuery();
	int resultPer = 10;
	int total = 0;
	int pages = 0;
	String[] list;
	int current = 1;
	
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
    }

	/**
	 * Initialize the servlet to the jsp page
	 */
	public void init(ServletConfig config) throws ServletException {

	}
	
	/**
	 * 	Process data before send it to jsp page
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] action = request.getParameterValues("act");
		if (action != null) {
			for (int i = 0; i<action.length; i++) {
				if (action[i].equals("next")) {
					current++;
				}
				if (action[i].equals("prev")) {
					current--;
				}
			}
		}
		String[] jump = request.getParameterValues("jumpto");
		if (jump != null) {
			current = Integer.valueOf(jump[0]);
		}

		
		request.setAttribute("dis", request.getParameter("dis"));
		request.setAttribute("zip", request.getParameter("zip"));
		request.setAttribute("state", request.getParameter("state"));
		request.setAttribute("city", request.getParameter("city"));
		request.setAttribute("list", list);
		request.setAttribute("pages", pages);
		request.setAttribute("current", current);
		request.setAttribute("total", total);
		RequestDispatcher rd = request.getRequestDispatcher("out.jsp");
		rd.forward(request, response);
	}

	/**
	 *	Process data after receiving submission from jsp page
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.getWriter().write("<title>Search Result</title>");
		
		String zip = null, city = null, state = null, di=null;
		zip = request.getParameter("zip");
		city = request.getParameter("city");
		state = request.getParameter("state");
		di = request.getParameter("dis");
		int dis = Integer.valueOf(di);

		try {
			String result = rq.read(zip, city, state, dis);
			total = rq.getCount();
			if (total%resultPer == 0) {
				pages = total/resultPer;
			} else {
				pages = total/resultPer + 1;
			}
			if (result.equals("")) {
				list = null;
			} else {
				list = result.split("<br>");				
			}
			doGet(request,response);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
