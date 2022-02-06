public class ProductView {
	
	protected int productid;
	public ProductView(int productid, String name, Float price, String image, String description) {
		super();
		this.productid = productid;
		this.name = name;
		this.price = price;
		this.image = image;
		this.description = description;
	}
	
	public ProductView(){}
	
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	protected String name;
	protected Float price;
	protected String image;
	protected String description;
}