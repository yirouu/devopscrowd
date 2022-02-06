
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jdbcURL = "jdbc:mysql://localhost:3306/devops";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	// Prepare list of SQL prepared statements to perform CRUD to our database
	private static final String GET_CART_PRODUCTS = "select * from product where productid=?";
	private static final String CHECK_OUT = "insert into orders (`orderDateTime`, `orderUserId`, `productId`, `orderStatus`)  values(?,?,?,?)";
	private static final String SELECT_USER_BY_ID = "select userid from user where userid =?";

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
	public CartServlet() {
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

		String action = request.getServletPath();
		try {
			switch (action) {
			case "/CartServlet/cart":
				getCartProducts(request, response);
				break;
			case "/CartServlet/quantity":
				updateQuantity(request, response);
				break;
			case "/CartServlet/remove":
				removeProduct(request, response);
				break;
			case "/CartServlet/checkout":
				checkout(request, response);
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

	private void getCartProducts(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		response.setContentType("text/html"); // Step 1: Initialize a PrintWriter object to return the html values via
		// the response
		PrintWriter out = response.getWriter();
		// get parameter passed in the URL
		List<Cart> products = new ArrayList<Cart>();

		HttpSession session = request.getSession();
		ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
		float sum = 0;

		try (Connection connection = getConnection();) {
			if (cart_list.size() > 0) {
				for (Cart item : cart_list) {
					PreparedStatement preparedStatement = connection.prepareStatement(GET_CART_PRODUCTS);
					preparedStatement.setInt(1, item.getProductid());
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						Cart cart = new Cart();
						cart.setProductid(rs.getInt("productid"));
						cart.setName(rs.getString("name"));
						cart.setPrice(rs.getFloat("price") * item.getQuantity());
						cart.setImage(rs.getString("image"));
						cart.setDescription(rs.getString("description"));
						cart.setQuantity(item.getQuantity());
						products.add(cart);
						sum += rs.getFloat("price") * item.getQuantity();
					}
				}
			}
			else {
				PrintWriter writer = response.getWriter();
				writer.println("<h1>" + "nothing in cart" + "</h1>");
				writer.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("cart_list", products);
		request.setAttribute("total", sum);
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	private void updateQuantity(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

			if (action != null && id >= 1) {
				if (action.equals("inc")) {
					for (Cart c : cart_list) {
						if (c.getProductid() == id) {
							int quantity = c.getQuantity();
							quantity++;
							c.setQuantity(quantity);
							response.sendRedirect("cart");
						}
					}
				}

				if (action.equals("dec")) {
					for (Cart c : cart_list) {
						if (c.getProductid() == id && c.getQuantity() > 1) {
							int quantity = c.getQuantity();
							quantity--;
							c.setQuantity(quantity);
							break;
						}
					}
					response.sendRedirect("cart");
				}
			} else {
				response.sendRedirect("cart");
			}
		}

	}

	private void removeProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String productId = request.getParameter("id");
			if (productId != null) {
				ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				if (cart_list != null) {
					for (Cart c : cart_list) {
						if (c.getProductid() == Integer.parseInt(productId)) {
							cart_list.remove(cart_list.indexOf(c));
							break;
						}
					}
				}
				response.sendRedirect("cart");

			} else {
				response.sendRedirect("cart");
			}

		}
	}

	private void checkout(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try (Connection connection = getConnection();) {
			Class.forName("com.mysql.jdbc.Driver");
			PreparedStatement ps = connection.prepareStatement(CHECK_OUT);
			PreparedStatement preparedS = connection.prepareStatement(SELECT_USER_BY_ID);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
			Date date = new Date();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			HttpSession sessions = request.getSession();
			String cuserid = (String) sessions.getAttribute("cuser");
			preparedS.setString(1, cuserid);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedS.executeQuery();
			// Step 4: Process the ResultSet object
			rs.next();
			int userid = rs.getInt("userid");

			if (cart_list != null && cuserid != null) {
				for (Cart c : cart_list) {
					ps.setString(1, formatter.format(date));
					ps.setInt(2, userid);
					ps.setInt(3, c.getProductid());
					ps.setString(4, "pending");

					int i = ps.executeUpdate();

				}
				cart_list.clear();
				response.sendRedirect("http://localhost:8090/devopscrowd/ProductViewServlet/dashboard");
			} else {
				if (cuserid == null) {
					PrintWriter writer = response.getWriter();
					writer.println("<h1>" + "you are not logged in" + "</h1>");
					writer.close();
				}
				response.sendRedirect("cart");
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

}
