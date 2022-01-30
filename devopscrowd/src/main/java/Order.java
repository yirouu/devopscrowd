
public class Order {
	protected int orderid;
	public Order(int orderid, String orderDateTime, int orderUserId, int productId, String orderStatus) {
		super();
		this.orderid = orderid;
		this.orderDateTime = orderDateTime;
		this.orderUserId = orderUserId;
		this.productId = productId;
		this.orderStatus = orderStatus;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	public int getOrderUserId() {
		return orderUserId;
	}
	public void setOrderUserId(int orderUserId) {
		this.orderUserId = orderUserId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	protected String orderDateTime;
	protected int orderUserId;
	protected int productId;
	protected String orderStatus;
}
