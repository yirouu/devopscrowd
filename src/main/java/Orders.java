
public class Orders {
	public Orders(int orderid, String orderDateTime, int orderUserId, int productId, String orderStatus, String name,
			int price, String image, String username, String email, String address, int postal) {
		super();
		this.orderid = orderid;
		this.orderDateTime = orderDateTime;
		this.orderUserId = orderUserId;
		this.productId = productId;
		this.orderStatus = orderStatus;
		this.name = name;
		this.price = price;
		this.image = image;
		this.username = username;
		this.email = email;
		this.address = address;
		this.postal = postal;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPostal() {
		return postal;
	}
	public void setPostal(int postal) {
		this.postal = postal;
	}
	protected int orderid;
	protected String orderDateTime;
	protected int orderUserId;
	protected int productId;
	protected String orderStatus;
	protected String name;
	protected int price;
	protected String image;
	protected String username;
	protected String email;
	protected String address;
	protected int postal;
}
