import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	// Prepare list of SQL prepared statements to perform CRUD to our database
	private static final String SELECT_ORDER_BY_ID = "select orderid, orderDateTime, orderUserId, productId, orderStatus from orders where orderid =?";
	private static final String SELECT_ALL_ORDERS = "SELECT DISTINCT orders.*,product.name,product.price,product.image,user.username,user.email,user.address, user.postal FROM orders JOIN product ON orders.productId = product.productid JOIN user ON orders.orderUserId = user.userid";
	private static final String DELETE_ORDER_SQL = "delete from orders where orderid = ?;";
	private static final String UPDATE_ORDER_SQL = "update orders set orderStatus = ? where orderid = ?;";

	// Implement the getConnection method which facilitates connection to the
	// database via JDBC
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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());

		// Step 4: Depending on the request servlet path, determine the function to
		// invoke using the follow switch statement.

		String action = request.getServletPath();
		try {
			switch (action) {
			case "/OrderServlet/edit":
				showEditForm(request, response);
				break;
			case "/OrderServlet/update":
				updateOrder(request, response);
				break;
			case "/OrderServlet/delete":
				deleteOrder(request, response);
				break;
			case "/OrderServlet/dashboard":
				listOrders(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
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
	public void listOrders(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Orders> orders = new ArrayList<>();
		try (Connection connection = getConnection();
				// Step 5.1: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);) {
			// Step 5.2: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				int orderid = rs.getInt("orderid");
				String orderDateTime = rs.getString("orderDateTime");
				int orderUserId = rs.getInt("orderUserId");
				int productId = rs.getInt("productId");
				String orderStatus = rs.getString("orderStatus");
				String productName = rs.getString("name");
				int productPrice = rs.getInt("price");
				String productImage = rs.getString("image");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String address = rs.getString("address");
				int postal = rs.getInt("postal");
				orders.add(new Orders(orderid, orderDateTime, orderUserId, productId, orderStatus, productName,
						productPrice, productImage, username, email, address, postal));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		PrintWriter out = response.getWriter();
		out.write("retrieved all orders successfully...");
		// Step 5.4: Set the users list into the listUsers attribute to be pass to the
		// orderManagement.jsp
		request.setAttribute("listOrders", orders);
		request.getRequestDispatcher("/orderManagement.jsp").forward(request, response);
	}

	// method to get parameter, query database for existing user data and redirect
	// to user edit page
	public void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get parameter passed in the URL
		String orderid = request.getParameter("orderid");
		Order existingOrder = new Order(0, "", 0, 0, "");
		// Establishing a Connection
		try (Connection connection = getConnection();
				// Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID);) {
			preparedStatement.setString(1, orderid);
			// Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Process the ResultSet object
			while (rs.next()) {
				int orderId = rs.getInt("orderid");
				String orderDateTime = rs.getString("orderDateTime");
				int orderUserId = rs.getInt("orderUserId");
				int productId = rs.getInt("productId");
				String orderStatus = rs.getString("orderStatus");
				existingOrder = new Order(orderId, orderDateTime, orderUserId, productId, orderStatus);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		PrintWriter out = response.getWriter();
		out.write("retrieved successfull...");
		// Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("order", existingOrder);
		request.getRequestDispatcher("/editOrder.jsp").forward(request, response);
	}

	// method to update the user table base on the form data
	public void updateOrder(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
	// Retrieve value from the request
	String orderid = request.getParameter("orderid");
	String orderStatus = request.getParameter("orderStatus");

	 // Attempt connection with database and execute update user SQL query
	 try (Connection connection = getConnection(); PreparedStatement statement =
	connection.prepareStatement(UPDATE_ORDER_SQL);) {
	 statement.setString(1, orderStatus);
	 statement.setString(2, orderid);
	 int i = statement.executeUpdate();
	 }
	 
	 PrintWriter out = response.getWriter();
	 out.write("update successfull...");
	 // redirect back to OrderServlet
	 response.sendRedirect("http://localhost:8090/devopscrowd/OrderServlet/dashboard");
	}
	
	// method to delete order
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	 String orderid = request.getParameter("orderid");
	 //Step 2: Attempt connection with database and execute delete order SQL query
	 try (Connection connection = getConnection(); PreparedStatement statement =
	connection.prepareStatement(DELETE_ORDER_SQL);) {
	 statement.setString(1, orderid);
	 int i = statement.executeUpdate();
	 }
	 
	 PrintWriter out = response.getWriter();
	 out.write("delete successfull...");
	 //Step 3: redirect back to OrderServlet dashboard
	 response.sendRedirect("http://localhost:8090/devopscrowd/OrderServlet/dashboard");
	}


}
