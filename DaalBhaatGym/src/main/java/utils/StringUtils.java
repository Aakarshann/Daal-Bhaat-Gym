package utils;

public class StringUtils {

	// Start: DB Connection
		public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
		public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/daalbhaatgym";
		public static final String LOCALHOST_USERNAME = "root";
		public static final String LOCALHOST_PASSWORD = "";
		
		public static final String IMAGE_ROOT_PATH = "Users\\user\\eclipse-workspace\\DaalBhaatGym\\src\\main\\webapp\\resources\\images\\";
		public static final String IMAGE_DIR_PRODUCT = "C:/" + IMAGE_ROOT_PATH + "product\\";
		public static final String IMAGE_DIR_USER = "C:/" + IMAGE_ROOT_PATH + "user\\";
		
	// End: DB Connection	

		// Start: Queries
		public static final String QUERY_REGISTER_USER = "INSERT INTO user ("
				+ "username, password, firstName, lastName, email, phoneNumber,image) "
				+ "VALUES (?, ?, ?, ?, ?, ? ,?)";
		public static final String QUERY_REGISTER_PLAN ="INSERT INTO plans ("
				+ "planDurationDays, planPrice, planDescription,planID, adminID) "
				+ "VALUES ( ?, ?, ? ,?, 1)";
		public static final String QUERY_LOGIN_USER_CHECK = "SELECT * FROM user WHERE username = ?";
		public static final String QUERY_GET_ALL_USER = "SELECT * FROM user";
		public static final String QUERY_GET_USER_ID = "SELECT userID FROM user WHERE username = ?";
		public static final String QUERY_DELETE_PLAN = "DELETE FROM plans WHERE planID = ?";
		public static final String QUERY_GET_ALL_PLANS = "SELECT * FROM plans";
		public static final String QUERY_GET_ADMIN_INFO= "SELECT * FROM admin WHERE username= ?";
		public static final String QUERY_UPDATE_PLAN = "UPDATE plans "
			    + "SET planDurationDays = ?, planPrice = ?, planDescription = ? "
			    + "WHERE planID = ?";
		public static final String QUERY_CHECK_EMAIL_EXISTS = "SELECT * FROM user WHERE email = ?";
	    public static final String QUERY_CHECK_NUMBER_EXISTS = "SELECT * FROM user WHERE phoneNumber = ?";
	    public static final String QUERY_CHECK_USERNAME_EXISTS = "SELECT * FROM user WHERE username = ?";
	    public static final String QUERY_CHECK_PRODUCT ="SELECT* FROM user WHERE product_Name = ?";

	// End: Queries

		
		//End: Queries
		
		//Start: Parameter names
		public static final String USERNAME = "username";
		public static final String FIRST_NAME = "firstName";
		public static final String LAST_NAME = "lastName";
		public static final String EMAIL = "email";
		public static final String PHONE_NUMBER = "phoneNumber";
		public static final String PASSWORD = "password";
		public static final String RETYPE_PASSWORD = "retypePassword";
		public static final String IMAGE = "image";
		public static final String PLAN_DURATION_DAYS = "planDurationDays";
		public static final String PLAN_PRICE = "planPrice";
		public static final String PLAN_DESCRIPTION = "planDescription";
		public static final String PLAN_ID = "planID";
		//End: Parameter names
		

		// Start: Validation Messages
		// Register Page Messages
		public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
		public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
		public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
		public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
		public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
		public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";
		public static final String MESSAGE_ERROR_INCORRECT_DATA = "Please correct all the fields.";

		// Login Page Messages
		public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
		public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
		public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";

		// Other Messages
		public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
		public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
		public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";
		
		public static final String MESSAGE_SUCCESS = "successMessage";
		public static final String MESSAGE_ERROR = "errorMessage";
		// End: Validation Messages

		// Start: JSP Route
		public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
		public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
		public static final String PAGE_URL_PLAN = "/pages/plan.jsp";
		public static final String PAGE_URL_ADMIN = "/pages/adminpage.jsp";
		public static final String PAGE_URL_HOME = "/home.jsp";
		public static final String PAGE_URL_FOOTER = "pages/footer.jsp";
		public static final String PAGE_URL_HEADER = "pages/header.jsp";
		public static final String URL_LOGIN = "/login.jsp";
		public static final String PAGE_URL_INDEX = "/index.jsp";
		
		
		// End: JSP Route

		// Start: Servlet Route
		public static final String SERVLET_URL_LOGIN = "/login";
		public static final String SERVLET_URL_REGISTER = "/registerServlet";
		public static final String SERVLET_URL_PLAN = "/PlanServlet";
		public static final String SERVLET_URL_LOGOUT = "/LogoutServlet";
		public static final String SERVLET_URL_HOME = "/HomeServlet";
		public static final String SERVLET_URL_MANAGE_PLAN= "/ManagePlanServlet";
		public static final String SERVLET_URL_ADMIN_SERVLET= "/AdminServlet";

		// End: Servlet Route

		// Start: Normal Text
		public static final String USER = "user";
		public static final String SUCCESS = "success";
		public static final String TRUE = "true";
		public static final String JSESSIONID = "JSESSIONID";
		public static final String LOGIN = "Login";
		public static final String LOGOUT = "Logout";
		public static final String STUDENT_MODEL = "studentModel";
		public static final String PLAN_LISTS = "planLists";
		public static final String USER_LISTS = "userLists";
		public static final String SLASH= "/";
		public static final String DELETE_ID= "deleteId";
		public static final String UPDATE_ID= "updateId";
		public static final String LOGGED_IN_USER="loggedInUser";
		
		
		
		
		// End: Normal Text
}
