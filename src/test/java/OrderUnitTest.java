import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		when(request.getRequestDispatcher(PAGE)).thenReturn(dispatcher);

		assertNotNull(testoc);
		System.out.println(testoc);

		orderServlet.listOrders(request, response);
		verify(request, times(1)).getRequestDispatcher(PAGE);
		verify(request, never()).getSession();
		verify(dispatcher).forward(request, response);
	}

	@Test
	void testShowEditForm() throws SQLException, IOException, ServletException {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteOrder() {
		fail("Not yet implemented");
	}

}
