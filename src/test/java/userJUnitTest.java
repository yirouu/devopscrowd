import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.springframework.mock.web.MockHttpSession;

@TestMethodOrder(OrderAnnotation.class)
class userJUnitTest extends Mockito {

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

	private final int accountsize = 4;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		uc = new UserCollection();

		uc.addUser(new User(01, "name1", "email1", "pw1", "user", "add1", 489515));
		uc.addUser(new User(02, "name2", "email2", "pw2", "user", "add2", 546148));
		uc.addUser(new User(03, "name3", "email3", "pw3", "user", "add3", 948715));
		uc.addUser(new User(04, "name4", "email4", "pw4", "user", "add4", 498615));

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(1)
	void testReg() throws ServletException, IOException {
//		fail("Not yet implemented");

//		when(request.getSession()).thenReturn(session);
//
//		when(request.getParameter("yourName")).thenReturn("test");
//		when(request.getParameter("yourEmail")).thenReturn("test");
//		when(request.getParameter("yourPassword")).thenReturn("test");
//		when(request.getParameter("yourAddress")).thenReturn("test");
//		when(request.getParameter("yourPostal")).thenReturn("123456");
//
//		StringWriter StringWriter = new StringWriter();
//		PrintWriter PrintWriter = new PrintWriter(StringWriter);
//
//		when(response.getWriter()).thenReturn(PrintWriter);
//
//		new RegisterServlet().doPost(request, response);
//
//		verify(request, atLeast(1)).getParameter("yourName");
//		verify(request, atLeast(1)).getParameter("yourEmail");
//		verify(request, atLeast(1)).getParameter("yourPassword");
//		verify(request, atLeast(1)).getParameter("yourAddress");
//		verify(request, atLeast(1)).getParameter("yourPostal");
//
//		try {
//			verify(session, times(0)).setAttribute("email", "exist");
//		} catch (MockitoAssertionError error) {
//			throw new MockitoAssertionError("Account Already Exist");
//		}
//
//		System.out.println("register successful");

		testuser = new User(05, "test", "test@gmail.com", "password", "user", "test", 123456);

		List<User> testuc = uc.getUser();
		uc.regUser(testuser);

		assertEquals(testuc.size(), accountsize + 1);
		System.out.println("register successful");

	}

	@Test
	@Order(2)
	void testLogin() throws ServletException, IOException {
//		fail("Not yet implemented");

//		when(request.getSession()).thenReturn(session);
//
//		when(request.getParameter("yourEmail")).thenReturn("test");
//		when(request.getParameter("yourPassword")).thenReturn("test");
//
//		StringWriter StringWriter = new StringWriter();
//		PrintWriter PrintWriter = new PrintWriter(StringWriter);
//
//		when(response.getWriter()).thenReturn(PrintWriter);
//
//		when(request.getAttribute("cuser")).thenReturn(123);
//
//		new LoginServlet().doPost(request, response);
//
//		verify(request, atLeast(1)).getParameter("yourEmail");
//		verify(request, atLeast(1)).getParameter("yourPassword");
//
//		try {
//			verify(session, times(0)).setAttribute("credential", "incorrect");
//		} catch (MockitoAssertionError error) {
//			throw new MockitoAssertionError("Login failed due to wrong credentials");
//		}
//		verify(session).setAttribute("isLoggedIn", "LoggedIn");
//
//		System.out.println("login successful");
		
		User loginUser = uc.loginUser("email1", "pw1");

		if (loginUser != null) {
			System.out.println("login successful");
		} else {
			System.out.println("login failed");
		}
		
	}

	@Test
	@Order(3)
	void testlistUser() throws ServletException, IOException {
		List<User> testuc = uc.getUser();
		User cuser = uc.findUserById(2);
		System.out.println("get user successful");
		
	}

	@Test
	@Order(4)
	void testUpdate() throws ServletException, IOException {
		
		int id = 1;
		String username = "test1";
		String email = "test1";
		String password = "test1";
		String address = "test1";
		int postal = 432867;
		
		User updated = uc.updateUser(id, username, email, password, address, postal);
		
		assertEquals(updated.getEmail(), "test1");
		
		System.out.println("update user successful");
	}
	
	@Test
	@Order(5)
	void testDelete() throws ServletException, IOException {

		uc.deleteUser(1);
		List<User> testa = uc.getUser();

		assertEquals(testa.size(), 3);
		System.out.println("deleted user successful");
	}


}
