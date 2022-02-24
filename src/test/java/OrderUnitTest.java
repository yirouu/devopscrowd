import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class OrderUnitTest extends Mockito {
	private OrderCollection oc;
	private Orders o1;
	private Orders o2;
	private Orders o3;
	private Orders o4;

	private static final String PAGE = "/orderManagement.jsp";
	private static final String EDITPAGE = "/editOrder.jsp";

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	HttpSession session;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		oc = new OrderCollection();
		o1 = new Orders(001, "2022-01-28 16:50:47", 001, 001, "pending", "product1", 0, "image1", "user1", "email1",
				"address1", 001);
		o2 = new Orders(002, "2022-01-28 16:50:47", 002, 002, "pending", "product2", 0, "image2", "user2", "email2",
				"address2", 002);
		o3 = new Orders(003, "2022-01-28 16:50:47", 003, 003, "pending", "product3", 0, "image3", "user3", "email3",
				"address3", 003);
		o4 = new Orders(004, "2022-01-28 16:50:47", 004, 004, "pending", "product4", 0, "image4", "user4", "email4",
				"address4", 004);
		oc.add(o1);
		oc.add(o2);
		oc.add(o3);
		oc.add(o4);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testListOrders() throws SQLException, IOException, ServletException {
		OrderServlet orderServlet = new OrderServlet();
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);

		List<Orders> testoc = oc.getOrders();
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		when(response.getWriter()).thenReturn(pw);

		when(request.getAttribute("listorders")).thenReturn(testoc);
		when(request.getRequestDispatcher(PAGE)).thenReturn(dispatcher);

		assertNotNull(testoc);
		System.out.println(testoc);
		
		
		orderServlet.listOrders(request, response);
		// verify that it request
		verify(request, times(1)).getRequestDispatcher(PAGE);
		verify(request, never()).getSession();
		verify(dispatcher).forward(request, response);
		String result = sw.getBuffer().toString().trim();

		System.out.println("Result: " + result);
	
	}

	@Test
	void testShowEditForm() throws SQLException, IOException, ServletException {
		OrderServlet orderServlet = new OrderServlet();
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		
		when(request.getParameter("orderid")).thenReturn("001");
		//Order existingorder = new Order(0, null, 0, 0, null);
		List<Orders> testoc = oc.getOrders();
		System.out.println(testoc);
		
		Orders order = oc.findOrdersById(001);
		System.out.println(order);
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		when(response.getWriter()).thenReturn(pw);
		when(request.getAttribute("listorders")).thenReturn(order);
		when(request.getRequestDispatcher(EDITPAGE)).thenReturn(dispatcher);
		
		
		orderServlet.showEditForm(request, response);
		// verifying that orderid was called
		String result = sw.getBuffer().toString().trim();

		System.out.println("Result: " + result);
		assertEquals("retrieved successfull...", result);
		verify(request, atLeast(1)).getParameter("orderid");
		verify(dispatcher).forward(request, response);
	}

	@Test
	void testUpdateOrder() throws SQLException, IOException, ServletException  {
		OrderServlet orderServlet = new OrderServlet();
		
		when(request.getParameter("orderid")).thenReturn("001");
		when(request.getParameter("orderstatus")).thenReturn("pending");

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		when(response.getWriter()).thenReturn(pw);
		
		orderServlet.updateOrder(request, response);
		String result = sw.getBuffer().toString().trim();

		System.out.println("Result: " + result);
	}

	@Test
	void testDeleteOrder() throws SQLException, IOException, ServletException  {
		OrderServlet orderServlet = new OrderServlet();
		
		when(request.getParameter("orderid")).thenReturn("001");
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		when(response.getWriter()).thenReturn(pw);
		
		orderServlet.deleteOrder(request, response);
		String result = sw.getBuffer().toString().trim();

		System.out.println("Result: " + result);
	}

}
