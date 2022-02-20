
import java.io.IOException;

//Import these libraries from java.io and java.sql
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		response.setContentType("text/html"); // Step 1: Initialize a PrintWriter object to return the html values via
												// the response
		PrintWriter out = response.getWriter(); // Step 2: retrieve the four parameters from the request from the web
												// form
		String e = request.getParameter("yourEmail");
		String p = request.getParameter("yourPassword");
		// Step 3: attempt connection to database using JDBC, you can change the
		// username and password accordingly using the phpMyAdmin > User Account dashboard
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/devops", "root", "password");
			// Step 4: implement the sql query using prepared statement
			// (https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html)
			PreparedStatement ps = con.prepareStatement("select * from user where email = ?");
			// Step 5: parse in the data retrieved from the web form request into the
			// prepared statement accordingly
			ps.setString(1, e);
			// Step 6: perform the query on the database using the prepared statement
			ResultSet rs = ps.executeQuery(); // Step 7: check if the query had been successfully execute, return “You
												// are successfully registered” via the response,
			if (rs.next()) {
				String userid = null;
				userid = rs.getString(1);
				PreparedStatement psp = con.prepareStatement("select password from user where userid = ?");
				psp.setString(1, userid);
				ResultSet rsp = psp.executeQuery();
				String userpassword = null;
				rsp.next();
				userpassword = rsp.getString(1);

				if (p.equals(userpassword)) {

					PreparedStatement psr = con.prepareStatement("select role from user where userid = ?");
					psr.setString(1, userid);
					ResultSet rsr = psp.executeQuery();
					rsr.next();
					String role = rsr.getString(1);

					HttpSession sessions = request.getSession();
					
					sessions.setAttribute("cuser", userid);
					
					sessions.setAttribute("isLoggedIn", "LoggedIn");
					
					if (role.equals("admin")) {
						
						sessions.setAttribute("role", "admin");
						response.sendRedirect("/devopscrowd/admin.jsp");

					} else {
						sessions.setAttribute("role", "user");
						response.sendRedirect("/devopscrowd/ProductViewServlet/dashboard");
					}
					

				} else {
					/*
					 * PrintWriter writerpe = response.getWriter(); writerpe.println("<h1>" +
					 * "wrongpassword!" + userpassword + "!="+ p + "<h1>"); writerpe.close();
					 */
					request.getSession().setAttribute("credential", "incorrect");
					response.sendRedirect("/devopscrowd/login.jsp");
				}

			} else {
				/*
				 * PrintWriter writerpe = response.getWriter(); writerpe.println("<h1>" +
				 * "wrongemail!" + e + "<h1>"); writerpe.close();
				 */
				request.getSession().setAttribute("credential", "incorrect");
				response.sendRedirect("/devopscrowd/login.jsp");

			}
		} // Step 8: catch and print out any exception
		catch (Exception exception) {
			System.out.println(exception);
			out.close();
		}

		doGet(request, response);
	}

}
