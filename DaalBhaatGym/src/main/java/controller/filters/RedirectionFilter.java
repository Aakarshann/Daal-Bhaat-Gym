package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.StringUtils;

public class RedirectionFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		 // Cast request and response objects to HttpServletRequest and HttpServletResponse for type safety
		HttpServletRequest req =(HttpServletRequest) request;
	    HttpServletResponse res=(HttpServletResponse) response;

	    // Get the requested URI
	    String uri = req.getRequestURI();

	    // Allow access to static resources (CSS) and the index page without checking login
	    if (uri.endsWith(".css") || uri.endsWith(".png") || uri.endsWith(".jpg")) {
	        chain.doFilter(request, response);
	        return;
	    }
	    
	    
	    // Check if the user is trying to access login-related pages
	    boolean isLoginPage = uri.endsWith(StringUtils.PAGE_URL_LOGIN)
	            || uri.endsWith(StringUtils.SERVLET_URL_LOGIN)
	            || uri.endsWith(StringUtils.SERVLET_URL_LOGOUT)
	            || uri.endsWith(StringUtils.PAGE_URL_REGISTER);

	    HttpSession session = req.getSession(false); // Don't create a new session if one doesn't exist
	    boolean isLoggedIn = (session != null && session.getAttribute(StringUtils.USERNAME) != null);

	    // Redirect to login if user is not logged in and trying to access a protected resource
	    if (!isLoggedIn && !isLoginPage) {
	    	System.out.println("hello");
	       // res.sendRedirect(req.getContextPath() + StringUtils.PAGE_URL_LOGIN);
	    	request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
	        return;
	    }
	    
	    if(isLoggedIn && uri.endsWith(StringUtils.SERVLET_URL_LOGOUT))
	    {
	    	System.out.println("logout");
	    	request.getRequestDispatcher(StringUtils.SERVLET_URL_LOGOUT).forward(req, res);
	    }

	    // Redirect logged-in users to the index page if trying to access login page again
	    if (isLoggedIn && isLoginPage) {
	    	System.out.println("hellooo23345");
	    	//request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
	        res.sendRedirect( StringUtils.PAGE_URL_INDEX);
	        return;
	    }
	    if(uri.endsWith(StringUtils.PAGE_URL_PLAN)) {
			request.getRequestDispatcher(StringUtils.SERVLET_URL_PLAN).forward(request, response);
	        return;
    	}
	    if(uri.endsWith(StringUtils.PAGE_URL_ADMIN)) {
			request.getRequestDispatcher(StringUtils.SERVLET_URL_ADMIN_SERVLET).forward(request, response);
	        return;
    	}
	    if(uri.endsWith(StringUtils.PAGE_URL_HOME)) {
			request.getRequestDispatcher(StringUtils.SERVLET_URL_HOME).forward(request, response);
	        return;
    	}
	    

	    // Allow access to the requested resource if user is logged in or accessing unprotected resources
	    chain.doFilter(request, response);		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
