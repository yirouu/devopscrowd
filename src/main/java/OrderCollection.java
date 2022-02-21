import java.util.ArrayList;
import java.util.List;

public class OrderCollection {
	private ArrayList<Orders> orders = new ArrayList<>();
	private int capacity;

	public OrderCollection() {
		// orders.add(new Orders(001, "2022-01-28 16:50:47", 001, 001, "pending",
		// "product1", 0, "image1", "user1","email1", "address1", 001));
		// orders.add(new Orders(002, "2022-01-28 16:50:47", 002, 002, "pending",
		// "product2", 0, "image2", "user2","email2", "address2", 002));
		// orders.add(new Orders(003, "2022-01-28 16:50:47", 003, 003, "pending",
		// "product3", 0, "image3", "user3","email3", "address3", 003));
		// orders.add(new Orders(004, "2022-01-28 16:50:47", 004, 004, "pending",
		// "product4", 0, "image4", "user4","email4", "address4", 004));

		this.capacity = 4;
	}

	public OrderCollection(int capacity) {
		this.capacity = capacity;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void add(Orders order) {
		if (orders.size() != capacity) {
			orders.add(order);
		}
	}

}
