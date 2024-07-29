package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DBController;
import model.UserModel;
import utils.StringUtils;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
		this.dbController = new DBController();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		    // Fetch all user details from the database
		    ArrayList<UserModel> users = dbController.getAllUsersInfo();
		    request.setAttribute(StringUtils.USER_LISTS, users);
		    
		    // Retrieve the username of the logged-in user from the session
		    HttpSession userSession = request.getSession();
		    String loggedInUsername = (String) userSession.getAttribute(StringUtils.USERNAME);
		    
		    // Iterate through the list of users to find the user matching the logged-in username
		    UserModel loggedInUser = null;
		    for (UserModel user : users) {
		        if (user.getUsername().equals(loggedInUsername)) {
		            loggedInUser = user;
		            break; // Exit the loop since we found the user
		        }
		    }
		    
		    // If the logged-in user is found, render their details
		    if (loggedInUser != null) {
		        request.setAttribute(StringUtils.LOGGED_IN_USER, loggedInUser);
		        request.getRequestDispatcher(StringUtils.PAGE_URL_HOME).forward(request, response);
		        System.out.println(loggedInUser);
		    } else {
		    	  request.getRequestDispatcher(StringUtils.PAGE_URL_HOME).forward(request, response);
		    }
		    }
		


	


}
