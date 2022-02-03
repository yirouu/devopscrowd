public class Feedback {
	
	protected String comment;
	public Feedback(int userid, int productid, String comment, String email, String rating) {
		super();
		this.userid = userid;
		this.productid = productid;
		this.comment = comment;
		this.email = email;
		this.rating = rating;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	 public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}

	 protected int userid;
	 protected int productid;
	 protected String email;
	 protected String rating;

}
