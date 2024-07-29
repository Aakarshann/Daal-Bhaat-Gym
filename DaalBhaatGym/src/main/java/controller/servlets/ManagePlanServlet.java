package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.PlanModel;
import utils.StringUtils;

/**
 * Servlet implementation class ManagePlan
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ManagePlanServlet" })
public class ManagePlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagePlanServlet() {
        this.dbController = new DBController();
		
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String updateId = request.getParameter(StringUtils.UPDATE_ID);
		String deleteId = request.getParameter(StringUtils.DELETE_ID);

		if (updateId != null && !updateId.isEmpty()) {
			doUpdate(request, response);
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}

	}
	
	protected void doUpdate(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		 String planDurationDays = req.getParameter("planDurationDays");
		    String planPrice = req.getParameter("planPrice");
		    String planDescription = req.getParameter("planDescription");
		    String planID = req.getParameter("planID");

		    // Create a PlanModel object with the updated information
		    PlanModel plan = new PlanModel(planDurationDays, planPrice, planDescription, planDescription);
		   
		    plan.setPlanDurationDays(planDurationDays);
		    plan.setPlanPrice(planPrice);
		    plan.setPlanDescription(planDescription);
		    plan.setPlanID(planID);
		    System.out.println("hit bhairacha doupdate");

		    // Call the DBController to update the plan in the database
		    int result = dbController.updatePlan(plan);

		    if (result == 1) {
		        // Update successful
		        req.setAttribute(StringUtils.MESSAGE_SUCCESS, "Plan updated successfully");
		        resp.sendRedirect(req.getContextPath() + StringUtils.PAGE_URL_ADMIN);
		    } else {
		        // Update failed
		        req.setAttribute(StringUtils.MESSAGE_ERROR, "Failed to update plan");
		        resp.sendRedirect(req.getContextPath() + StringUtils.PAGE_URL_ADMIN);
		        System.out.println("failed plan update huhu");
		        System.out.println(result);
		    }
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("delete triggered");
		if (dbController.deletePlan(req.getParameter(StringUtils.DELETE_ID)) == 1) {
			req.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE);
			resp.sendRedirect(req.getContextPath() + StringUtils.PAGE_URL_ADMIN);
		} else {
			req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_DELETE);
			resp.sendRedirect(req.getContextPath() + StringUtils.PAGE_URL_ADMIN);
		}
	}

}
