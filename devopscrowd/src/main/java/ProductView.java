public class ProductView {
	public ProductView(int productid, String name, Float price, String image, String description, int quantity) {
		super();
		this.productid = productid;
		this.name = name;
		this.price = price;
		this.image = image;
		this.description = description;
		this.quantity = quantity;
	}
	protected int getproductid() {
		return productid;
	}
	protected void setproductid(int productid) {
		this.productid = productid;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected Float getPrice() {
		return price;
	}
	protected void setPrice(Float price) {
		this.price = price;
	}
	protected String getImage() {
		return image;
	}
	protected void setImage(String image) {
		this.image = image;
	}
	protected String getDescription() {
		return description;
	}
	protected void setDescription(String description) {
		this.description = description;
	}
	protected int getQuantity() {
		return quantity;
	}
	protected void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	protected int productid;
	protected String name;
	protected Float price;
	protected String image;
	protected String description;
	protected int quantity;

}