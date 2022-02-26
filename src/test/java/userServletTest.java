import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class userServletTest extends Mockito {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	HttpSession session;
	@Mock
	RequestDispatcher rd;

	private UserCollection uc;

	private User testuser;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		uc = new UserCollection();

		uc.addUser(new User(1, "name1", "email1", "pw1", "user", "add1", 489515));
		uc.addUser(new User(2, "name2", "email2", "pw2", "user", "add2", 546148));
		uc.addUser(new User(3, "name3", "email3", "pw3", "user", "add3", 948715));
		uc.addUser(new User(4, "name4", "email4", "pw4", "user", "add4", 498615));

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(1)
	void testDashboard() throws ServletException, IOException {

		testuser = new User(5, "test", "test@gmail.com", "password", "user", "test", 123456);

		/* fail("Not yet implemented"); */
		when(request.getServletPath()).thenReturn("/UserServlet/dashboard");

		when(request.getSession()).thenReturn(session);

		when(session.getAttribute("cuser")).thenReturn("5");
		when(request.getAttribute("GetUser")).thenReturn(testuser);

		when(request.getRequestDispatcher("/userprofile.jsp")).thenReturn(rd);

		StringWriter StringWriter = new StringWriter();
		PrintWriter PrintWriter = new PrintWriter(StringWriter);

		when(response.getWriter()).thenReturn(PrintWriter);

		new UserServlet().doGet(request, response);

		// verify
		verify(rd).forward(request, response);

		System.out.println("test dashboard successful");
	}

	@Test
	@Order(2)
	void testEditForm() throws ServletException, IOException {
		/* fail("Not yet implemented"); */

		when(request.getSession()).thenReturn(session);
		
		when(request.getServletPath()).thenReturn("/UserServlet/edit");

		when(request.getParameter("id")).thenReturn("1");

		when(request.getRequestDispatcher("/editprofile.jsp")).thenReturn(rd);

		User user = uc.findUserById(1);

		System.out.println(user);

		StringWriter StringWriter = new StringWriter();
		PrintWriter PrintWriter = new PrintWriter(StringWriter);

		when(request.getAttribute("user")).thenReturn(user);
		when(response.getWriter()).thenReturn(PrintWriter);

		new UserServlet().doGet(request, response);

		// verify
		verify(rd).forward(request, response);
		verify(request, atLeast(1)).getParameter("id");

		System.out.println("test edit form successful");
	}

	@Test
	@Order(3)
	void testUpdate() throws ServletException, IOException {
		/* fail("Not yet implemented"); */
		when(request.getSession()).thenReturn(session);

		when(request.getServletPath()).thenReturn("/UserServlet/update");

		when(request.getParameter("userid")).thenReturn("1");
		when(request.getParameter("username")).thenReturn("test1");
		when(request.getParameter("email")).thenReturn("test1");
		when(request.getParameter("password")).thenReturn("test1");
		when(request.getParameter("address")).thenReturn("test1");
		when(request.getParameter("postal")).thenReturn("987654");

		StringWriter StringWriter = new StringWriter();
		PrintWriter PrintWriter = new PrintWriter(StringWriter);

		when(response.getWriter()).thenReturn(PrintWriter);

		new UserServlet().doGet(request, response);

		verify(request, atLeast(1)).getParameter("userid");
		verify(request, atLeast(1)).getParameter("username");
		verify(request, atLeast(1)).getParameter("email");
		verify(request, atLeast(1)).getParameter("password");
		verify(request, atLeast(1)).getParameter("address");
		verify(request, atLeast(1)).getParameter("postal");

		System.out.println("test update successful");
	}

	@Test
	@Order(4)
	void testLogout() throws ServletException, IOException {
		/* fail("Not yet implemented"); */
		when(request.getSession()).thenReturn(session);
		when(request.getServletPath()).thenReturn("/UserServlet/logout");

		StringWriter StringWriter = new StringWriter();
		PrintWriter PrintWriter = new PrintWriter(StringWriter);

		when(response.getWriter()).thenReturn(PrintWriter);

		new UserServlet().doGet(request, response);

		verify(session).setAttribute("isLoggedIn", null);
		verify(session).setAttribute("role", null);

	}

	@Test
	@Order(5)
	void testDelete() throws ServletException, IOException {
		/* fail("Not yet implemented"); */
		when(request.getSession()).thenReturn(session);
		when(request.getServletPath()).thenReturn("/UserServlet/delete");
		
		when(request.getParameter("id")).thenReturn("1");

		StringWriter StringWriter = new StringWriter();
		PrintWriter PrintWriter = new PrintWriter(StringWriter);

		when(response.getWriter()).thenReturn(PrintWriter);
		
		new UserServlet().doGet(request, response);
		
		verify(request, atLeast(1)).getParameter("id");

		System.out.println("test delete successful");
	}
}
