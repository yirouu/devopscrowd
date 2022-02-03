
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Step 1: Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	// Step 2: Prepare list of SQL prepared statements to perform CRUD to our
	// database
	private static final String INSERT_USERS_SQL = "INSERT INTO user"
			+ " (username, email, password, role, address, postal) VALUES " + " (?, ?, ?, ?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "select userid, username, email, password, role, address, postal from user where userid =?";
	private static final String SELECT_ALL_USERS = "select * from user ";
	private static final String DELETE_USERS_SQL = "delete from user where userid = ?;";
	private static final String UPDATE_USERS_SQL = "update user set username = ?,email= ?, password =?,role =?, address =?, postal = ? where userid = ?;";

	// Step 3: Implement the getConnection method which facilitates connection to
	// the database via JDBC
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Step 4: Depending on the request servlet path, determine the function to
		// invoke using the follow switch statement.
	
		String action = request.getServletPath();
		 try {
		 switch (action) {
			/*
			 * case "/UserServlet/delete": deleteUser(request, response); break; case
			 * "/UserServlet/edit": showEditForm(request, response); break; case
			 * "/UserServlet/update": updateUser(request, response); break;
			 */
		 case "/UserServlet/dashboard":
		 listUsers(request, response);
		 break;
		 case "/UserServlet/logout":
				// delete session
				HttpSession sessions = request.getSession();
				response.sendRedirect(request.getContextPath() + "/login.jsp");
				sessions.setAttribute("isLoggedIn", null);
				sessions.setAttribute("cuser", null);
				sessions.setAttribute("role", null);
				sessions.invalidate();
				break;
		 }
		 } catch (SQLException ex) {
		 throw new ServletException(ex);
		 }
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// Step 5: listUsers function to connect to the database and retrieve all users
	// records
	private void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> users = new ArrayList<>();
		try (Connection connection = getConnection();
				// Step 5.1: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			// Step 5.2: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				int userid = rs.getInt("userid");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String role = rs.getString("role");
				String address = rs.getString("address");
				int postal = rs.getInt("postal");
				users.add(new User(userid, username, email, password, role, address, postal));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5.4: Set the users list into the listUsers attribute to be pass to the
		// userprofile.jsp
		request.setAttribute("listUsers", users);
		request.getRequestDispatcher("/userprofile.jsp").forward(request, response);
	}

}
