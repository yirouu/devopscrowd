import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderCollectionTest {
	private OrderCollection oc;
	private Orders o1;
	private Orders o2;
	private Orders o3;
	private Orders o4;
	private Orders o5;
	private final int ORDERS_SIZE = 4;

	@BeforeEach
	void setUp() throws Exception {
		oc = new OrderCollection();
		o1 = new Orders(001, "2022-01-28 16:50:47", 001, 001, "pending", "product1", 0, "image1", "user1", "email1",
				"address1", 001);
		o2 = new Orders(002, "2022-01-28 16:50:47", 002, 002, "pending", "product2", 0, "image2", "user2", "email2",
				"address2", 002);
		o3 = new Orders(003, "2022-01-28 16:50:47", 003, 003, "pending", "product3", 0, "image3", "user3", "email3",
				"address3", 003);
		o4 = new Orders(004, "2022-01-28 16:50:47", 004, 004, "pending", "product4", 0, "image4", "user4", "email4",
				"address4", 004);
		o5 = new Orders(005, "2022-01-28 16:50:47", 005, 005, "pending", "product5", 0, "image5", "user5", "email5",
				"address5", 005);
		oc.add(o1);
		oc.add(o2);
		oc.add(o3);
		oc.add(o4);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetOrders() {
		oc.getOrders();
		System.out.println(ORDERS_SIZE);
	}

	@Test
	void testAdd() {
		oc.add(o5);
		assertEquals(oc.getOrders().size(), 4);
	}

	@Test
	void testFindOrdersById() {
		int id = 002;
		oc.findOrdersById(id);
		assertEquals(oc.findOrdersById(id), o2);
		oc.findOrdersById(0);
		assertEquals(oc.findOrdersById(0), null);
	}

	@Test
	void testEditOrder() {
		int orderid = 001;
		int orderID = 01;
		String orderDateTime = "2022-01-28 16:50:47";
		int orderUserId = 01;
		int productId = 01;
		String orderStatus = "approved";
		String name = "product";
		int price = 10;
		String image = "image";
		String username = "username";
		String email = "email";
		String address = "address";
		int postal = 01;

		oc.editOrder(orderid, orderDateTime, orderUserId, productId, orderStatus, name, price, image, username, email,
				address, postal);
	}

	@Test
	void testDeleteOrder() {
		int id = 003;
		oc.deleteOrder(id);
	}

}
