package controller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxSql.StmtExecute;

import model.LoginModel;
import model.PasswordEncryptionWithAes;
import model.PlanModel;
import model.UserModel;
import utils.StringUtils;

public class DBController {
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(StringUtils.DRIVER_NAME);

		return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
				StringUtils.LOCALHOST_PASSWORD);

	}
	

	public int registerUser(UserModel User) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_USER);

			stmt.setString(1, User.getUsername());
			stmt.setString(2, PasswordEncryptionWithAes.encrypt(User.getUsername(), User.getPassword()));
			stmt.setString(3, User.getFirstName());
			stmt.setString(4, User.getLastName());
			stmt.setString(5, User.getEmail());
			stmt.setString(6, User.getPhoneNumber());
			stmt.setString(7, User.getImageUrlFromPart());

			int result = stmt.executeUpdate();

			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
			}

		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return -1; // Internal error
		}

	}


	public int getUserLoginInfo(LoginModel loginModel) {
		// Try-catch block to handle potential SQL or ClassNotFound exceptions
		try {
			// Prepare a statement using the predefined query for login check
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);

			// Set the username in the first parameter of the prepared statement
			st.setString(1, loginModel.getUsername());

			// Execute the query and store the result set
			ResultSet result = st.executeQuery();

			// Check if there's a record returned from the query
			if (result.next()) {
				// Get the username from the database
				String userDb = result.getString(StringUtils.USERNAME);

				// Get the password from the database
				String encryptedPwd = result.getString(StringUtils.PASSWORD);

				String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);
				// Check if the username and password match the credentials from the database
				if (userDb.equals(loginModel.getUsername()) && decryptedPwd.equals(loginModel.getPassword())) {
					// Login successful, return 1
					return 1;
				} else {
					// Username or password mismatch, return 0
					return 0;
				}
			} else {
				// Username not found in the database, return -1
				return -1;
			}

			// Catch SQLException and ClassNotFoundException if they occur
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return -2;
		}
	}

	public ArrayList<UserModel> getAllUsersInfo() {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.QUERY_GET_ALL_USER);
			ResultSet rs = st.executeQuery();

			ArrayList<UserModel> users = new ArrayList<>();

			while (rs.next()) {
				UserModel user = new UserModel();
				user.setUsername(rs.getString("username"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNumber(rs.getString("phoneNumber"));
				user.setImageUrlFromDB(rs.getString("image"));

				users.add(user);
			}
			return users;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<PlanModel> getAllPlanInfo() {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.QUERY_GET_ALL_PLANS);
			ResultSet rs = st.executeQuery();
			ArrayList<PlanModel> plans = new ArrayList<>();
			while (rs.next()) {
				PlanModel plan = new PlanModel();
				plan.setPlanDurationDays(rs.getString("planDurationDays"));
				plan.setPlanPrice(rs.getString("planPrice"));
				plan.setPlanDescription(rs.getString("planDescription"));
				plan.setPlanID(rs.getString("planID"));
				;

				plans.add(plan);
			}
			return plans;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public int registerPlan(PlanModel Plan) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_PLAN);

			stmt.setString(1, Plan.getPlanDurationDays());
			stmt.setString(2, Plan.getPlanPrice());
			stmt.setString(3, Plan.getPlanDescription());
			stmt.setString(4, Plan.getPlanID());
			int result = stmt.executeUpdate();

			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
			}

		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return -1; // Internal error
		}

	}

	public int deletePlan(String planID) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.QUERY_DELETE_PLAN);
			st.setString(1, planID);
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}

	public int updatePlan(PlanModel plan) {
		try (Connection con = getConnection()) {
			// Prepare the SQL statement to update plan information
			PreparedStatement stmt = con.prepareStatement(StringUtils.QUERY_UPDATE_PLAN);

			// Set the parameters for the SQL statement
			stmt.setString(1, plan.getPlanDurationDays());
			stmt.setString(2, plan.getPlanPrice());
			stmt.setString(3, plan.getPlanDescription());
			stmt.setString(4, plan.getPlanID());

			// Execute the update operation
			int result = stmt.executeUpdate();

			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Update successful
			} else {
				return 0; // Update failed (no rows affected)
			}
		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return -1; // Internal error
		}
	}



	public int getAdminInfo(LoginModel loginModel) {
	    try {
	        // Prepare a statement using the predefined query for admin info check
	        PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_GET_ADMIN_INFO);
	        st.setString(1, loginModel.getUsername());

	        // Execute the query and store the result set
	        ResultSet result = st.executeQuery();

	        // Check if there's a record returned from the query
	        if (result.next()) {
	            // Admin record found in the database
	            return 1;
	        } else {
	            // Admin record not found in the database
	            return 0;
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        // Print the stack trace for debugging purposes
	        ex.printStackTrace();
	        // Return -1 to indicate an internal error
	        return -1;
	    }
	}
	public Boolean checkIfEmailExists(String email) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtils.QUERY_CHECK_EMAIL_EXISTS);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null; // Handle exception appropriately
        }
    }

    public Boolean checkNumberIfExists(String phoneNumber) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtils.QUERY_CHECK_NUMBER_EXISTS);
            st.setString(1, phoneNumber);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null; // Handle exception appropriately
        }
    }

    public Boolean checkUsernameIfExists(String username) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtils.QUERY_CHECK_USERNAME_EXISTS);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null; // Handle exception appropriately
        }
    }

    
}
