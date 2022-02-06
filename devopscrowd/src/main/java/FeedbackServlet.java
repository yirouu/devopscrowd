import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class FeedbackServlet
 */
@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */ 
	//Step 1: Prepare list of variables used for database connections
	 private String jdbcURL = "jdbc:mysql://localhost:3306/feedbackdetails";
	 private String jdbcUsername = "root";
	 private String jdbcPassword = "password";
	 //Step 2: Prepare list of SQL prepared statements to perform CRUD to our database
	 private static final String SELECT_FEEDBACK_BY_ID = "select feedbackId, comment, rating from feedback where feedbackId =?";
		private static final String SELECT_ALL_FEEDBACK = "SELECT * from feedback";
		private static final String DELETE_FEEDBACK_SQL = "delete from feedback where feedbackId = ?;";
		private static final String UPDATE_FEEDBACK_SQL = "update feedback set comment = ?,rating= ? where feedbackId = ?;";
	 //Step 3: Implement the getConnection method which facilitates connection to the database via JDBC
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
    public FeedbackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Step 4: Depending on the request servlet path, determine the function to invoke using thfollow switch statement.
				String action = request.getServletPath();
				 try {
				 switch (action) {
				 case "/FeedbackServlet/delete":
				 deleteFeedback(request, response);
				 break;
				 case "/FeedbackServlet/edit":
				 showEditForm(request, response);
				 break;
				 case "/FeedbackServlet/update":
				 updateFeedback(request, response);
				 break;
				 case "/FeedbackServlet/dashboard":
				 listFeedbacks(request, response);
				 break;
				 }
				 } catch (SQLException ex) {
				 throw new ServletException(ex);
				 }
				 
				
			}
			//Step 5: listFeedback function to connect to the database and retrieve all feedback records
			 private void listFeedbacks(HttpServletRequest request, HttpServletResponse response)
			 throws SQLException, IOException, ServletException
			 {
			 List <Feedback> feedbacks = new ArrayList <>();
			  try (Connection connection = getConnection();
			  // Step 5.1: Create a statement using connection object
			  PreparedStatement preparedStatement =
			 connection.prepareStatement(SELECT_ALL_FEEDBACK);) {
			  // Step 5.2: Execute the query or update query
			  ResultSet rs = preparedStatement.executeQuery();
			  // Step 5.3: Process the ResultSet object.
			  while (rs.next()) {
				  int feedbackId = rs.getInt("feedbackId");
				  String comment = rs.getString("comment");
				  String rating = rs.getString("rating");
			  
			  feedbacks.add(new Feedback(feedbackId, comment, rating));
			  }
			  } catch (SQLException e) {
			  System.out.println(e.getMessage());
			  }
			 // Step 5.4: Set the feedback list into the listFeedback attribute to be pass to th feedbackManagement.jsp
			 request.setAttribute("listFeedbacks", feedbacks);
			 request.getRequestDispatcher("/feedbackList.jsp").forward(request, response);
			 }
			 
			//method to get parameter, query database for existing user data and redirect to user edit page
			 private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			 throws SQLException, ServletException, IOException {
			 //get parameter passed in the URL
				 System.out.println("feedbackId");
			 String feedbackid = request.getParameter("feedbackId");
			 Feedback existingFeedback = new Feedback(0,"","");
			 // Step 1: Establishing a Connection,
			 try (Connection connection = getConnection();
			 // Step 2:Create a statement using connection object
			 PreparedStatement preparedStatement =
			 connection.prepareStatement(SELECT_FEEDBACK_BY_ID);) {
			 preparedStatement.setString(1, feedbackid);
			 // Step 3: Execute the query or update query
			 ResultSet rs = preparedStatement.executeQuery();
			 // Step 4: Process the ResultSet object
			 while (rs.next()) {
				 int feedbackId = rs.getInt("feedbackId");
				  String comment = rs.getString("comment");
				  String rating = rs.getString("rating");
				 existingFeedback = new Feedback(feedbackId, comment, rating);
			 }
			 } catch (SQLException e) {
			 System.out.println(e.getMessage());
			 }
			 //Step 5: Set existingUser to request and serve up the userEdit form
			 request.setAttribute("feedbacks", existingFeedback);
			 request.getRequestDispatcher("/feedEdit.jsp").forward(request, response);
			 }
			 
			//method to update the user table base on the form data
			 private void updateFeedback(HttpServletRequest request, HttpServletResponse response)
			 throws SQLException, IOException {
			 //Step 1: Retrieve value from the request
		      String feedbackId = request.getParameter("feedbackId");
			  String comment = request.getParameter("comment");
			  String rating = request.getParameter("rating");

			  //Step 2: Attempt connection with database and execute update user SQL query
			  try (Connection connection = getConnection(); PreparedStatement statement =
			 connection.prepareStatement(UPDATE_FEEDBACK_SQL);) {
			  statement.setString(1, comment);
			  statement.setString(2, rating);
			  statement.setString(3, feedbackId);
			  int i = statement.executeUpdate();
			  }
			  //Step 3: redirect back to UserServlet (note: remember to change the url to your project name)
			  response.sendRedirect("http://localhost:8090/devopscrowd/FeedbackServlet/dashboard");
			 }
			 
			//method to delete feedback
			 private void deleteFeedback(HttpServletRequest request, HttpServletResponse response)
			 throws SQLException, IOException {
			 //Step 1: Retrieve value from the request
			  String feedbackId = request.getParameter("feedbackId");
			  //Step 2: Attempt connection with database and execute delete user SQL query
			  try (Connection connection = getConnection(); PreparedStatement statement =
			 connection.prepareStatement(DELETE_FEEDBACK_SQL);) {
			  statement.setString(1, feedbackId);
			  int i = statement.executeUpdate();
			  }
			  //Step 3: redirect back to UserServlet dashboard (note: remember to change the url to your project name)
			  response.sendRedirect("http://localhost:8090/devopscrowd/FeedbackServlet/dashboard");
			 }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

