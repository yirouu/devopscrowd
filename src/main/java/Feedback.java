public class Feedback {
	
	protected int feedbackId;
	public Feedback(int feedbackId, String comment, String rating) {
		super();
		this.feedbackId = feedbackId;
		this.comment = comment;
		this.rating = rating;
	}
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
	 protected String comment;
	 protected String rating;

	 

}

