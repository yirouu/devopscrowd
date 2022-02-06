

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateProductServlet
 */
@WebServlet("/CreateProductServlet")
public class CreateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter(); 
		String m = request.getParameter("productId");
		String n = request.getParameter("name");
		String p = request.getParameter("price");
		String e = request.getParameter("description");
		String c = request.getParameter("image");
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/devops", "root", "password");
		PreparedStatement ps = con.prepareStatement("insert into PRODUCT values(?,?,?,?,?)");
		ps.setString(1, m);
		ps.setString(2, n);
		ps.setString(3, p);
		ps.setString(4, e);
		ps.setString(5, c);
		int i = ps.executeUpdate(); 
		if (i > 0){
		PrintWriter writer = response.getWriter();
		writer.println("<h1>" + "You have successfully added a new product!" + "</h1>");
		writer.close();
		}
		} //Step 8: catch and print out any exception
		catch (Exception exception) {
		System.out.println(exception);
		out.close();
		}
	}

}
