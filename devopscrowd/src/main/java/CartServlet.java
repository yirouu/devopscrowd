
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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


}
