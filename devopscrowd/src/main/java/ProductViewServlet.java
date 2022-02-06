

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class ProductViewServlet
 */
@WebServlet("/ProductViewServlet")
public class ProductViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	
	//Prepare list of SQL prepared statements to perform CRUD to our database
	private static final String INSERT_PRODUCT_SQL = "INSERT INTO product" + " (productId, name, price, image, description) VALUES " + " (?, ?, ?);";
	private static final String SELECT_PRODUCT_BY_ID = "select productid,name,price,image,description from product where productid =?";
	private static final String SELECT_ALL_PRODUCTS = "select * from product ";
	private static final String DELETE_PRODUCTS_SQL = "delete from product where productId = ?;";
	private static final String UPDATE_PRODUCTS_SQL = "update product set productId =?,name = ?,price= ?, image =?,description =? where productId = ?;";

	//Implement the getConnection method which facilitates connection to the database via JDBC
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
    public ProductViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getServletPath();
		try {
		switch (action) {
		case "/ProductViewServlet/update":
			updateProduct(request, response);
		break;
		case "/ProductViewServlet/delete":
			deleteProduct(request, response);
		break;
		case "/ProductViewServlet/edit":
			editProduct(request, response);
		break;
		case "/ProductViewServlet/dashboard":
			listProduct(request, response);
		break;
		case "/ProductViewServlet/details":
			getDetails(request, response);
			break;
		}
		} catch (SQLException ex) {
		throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void listProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
			List <ProductView> products = new ArrayList <>();
			try (Connection connection = getConnection();
			// Step 5.1: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
			// Step 5.2: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
			int productId = rs.getInt("productid");
			String name = rs.getString("name");
			Float price = rs.getFloat("price");
			String image = rs.getString("image");
			String description = rs.getString("description");
			products.add(new ProductView(productId,name, price, image, description));
			}
			} catch (SQLException e) {
			System.out.println(e.getMessage());
			}
			// Step 5.4: Set the users list into the listUsers attribute to be pass to the userManagement.jsp
			request.setAttribute("listProduct", products);
			request.getRequestDispatcher("/product.jsp").forward(request, response);
			}
	
	private void editProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
		String productid = request.getParameter("productid");
		ProductView existingProduct = new ProductView(0, "", (float) 0, "","");
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
			preparedStatement.setString(1, productid);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				int productId = rs.getInt("productid");
				String name = rs.getString("name");
				Float price = rs.getFloat("price");
				String image = rs.getString("image");
				String description = rs.getString("description");
				existingProduct = new ProductView(productId,name, price, image, description);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("editproduct", existingProduct);
		request.getRequestDispatcher("/editProduct.jsp").forward(request, response);
	}
	
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
		String productId = request.getParameter("productId");
		//Step 2: Attempt connection with database and execute delete user SQL query
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_SQL);) {
		statement.setString(1, productId);
		int i = statement.executeUpdate();
		}
		response.sendRedirect("http://localhost:8090/devopscrowd//ProductViewServlet/dashboard");
		}

			private void updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
			String oriProductId = request.getParameter("oriProductId");
			String productid = request.getParameter("productId");
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String image = request.getParameter("image");
			String description = request.getParameter("description");
			//Step 2: Attempt connection with database and execute update user SQL query
			try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL);) {
			statement.setString(1, productid);
			statement.setString(2, name);
			statement.setString(3, price);
			statement.setString(4, image);
			statement.setString(5, description);
			statement.setString(6, oriProductId);
			int i = statement.executeUpdate();
			} 
			response.sendRedirect("http://localhost:8090/devopscrowd/ProductViewServlet/dashboard");
			}
	
			private void getDetails(HttpServletRequest request, HttpServletResponse response)
					throws SQLException, ServletException, IOException {
				// get parameter passed in the URL
				String productid = request.getParameter("productid");
				ProductView product = new ProductView(0,"",(float) 0,"","");
				// Establishing a Connection
				try (Connection connection = getConnection();
						// Create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
					preparedStatement.setString(1, productid);
					// Execute the query or update query
					ResultSet rs = preparedStatement.executeQuery();
					// Process the ResultSet object
					while (rs.next()) {
						int productId = rs.getInt("productid");
						String name = rs.getString("name");
						Float price = rs.getFloat("price");
						String image = rs.getString("image");
						String description = rs.getString("description");
						product = new ProductView(productId, name, price, image, description);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				// Step 5: Set existingUser to request and serve up the userEdit form
				request.setAttribute("productdetail", product);
				request.getRequestDispatcher("/productDetails.jsp").forward(request, response);
			}
			
	
}
