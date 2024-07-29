package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.PlanModel;
import model.UserModel;
import utils.StringUtils;
import utils.ValidationUtil;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(asyncSupported = true, urlPatterns =  {StringUtils.SERVLET_URL_ADMIN_SERVLET } )
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private final DBController dbController;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        this.dbController = new DBController();
		
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGetUsers(request, response);
		doGetPlans(request, response);
		request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN).forward(request, response);

		
	
	}
	protected void doGetUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<UserModel> users = dbController.getAllUsersInfo();
		request.setAttribute(StringUtils.USER_LISTS, users);
	}
	protected void doGetPlans(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<PlanModel> plans = dbController.getAllPlanInfo();
		request.setAttribute(StringUtils.PLAN_LISTS, plans);

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAddPlan(request, response);
	}
	protected void doAddPlan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String planDurationDays = request.getParameter(StringUtils.PLAN_DURATION_DAYS);
		String planPrice = request.getParameter(StringUtils.PLAN_PRICE);
		String planDescription = request.getParameter(StringUtils.PLAN_DESCRIPTION);
		String planID = request.getParameter(StringUtils.PLAN_ID);

		PlanModel plan = new PlanModel(planDurationDays, planPrice, planDescription, planID);

		if (!ValidationUtil.isNumbersOnly(planDurationDays) || !ValidationUtil.hasNoSpecialCharacters(planDescription)
				|| !ValidationUtil.isNumbersOnly(planPrice))
		{
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_INCORRECT_DATA);
			request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN).forward(request, response);
		}

		int result = dbController.registerPlan(plan);

		if (result == 1) {
//									
			//// Get the image file name from the student object (assuming it was extracted
			//// earlier)
			// String fileName = student.getImageUrlFromPart();
			//
			// Check if a filename exists (not empty or null)
			// if (!fileName.isEmpty() && fileName != null) {
			//// Construct the full image save path by combining the directory path and
			//// filename
			// String savePath = StringUtils.IMAGE_DIR_USER;
//									  imagePart.write(savePath + fileName);  // Save the uploaded image to the specified path
			System.out.println("1 ayo hai result");

			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN + "?success=true");
		} else if (result == 0) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_PLAN).forward(request, response);
			System.out.println("0 ayo hai result");
		} else {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_PLAN).forward(request, response);
			System.out.println("last walaa option ayo hai result");

		}

	}

	}


