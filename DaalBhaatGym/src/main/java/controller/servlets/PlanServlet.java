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
import utils.StringUtils;
import utils.ValidationUtil;

/**
 * Servlet implementation class PlanServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/PlanServlet" })
public class PlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlanServlet() {
		this.dbController = new DBController();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<PlanModel> plans = dbController.getAllPlanInfo();
		request.setAttribute(StringUtils.PLAN_LISTS, plans);
		request.getRequestDispatcher(StringUtils.PAGE_URL_PLAN).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_ADMIN + "?success=true");
		} else if (result == 0) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN).forward(request, response);
			System.out.println("0 ayo hai result");
		} else {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN).forward(request, response);
			System.out.println("last walaa option ayo hai result");

		}

	}

}
