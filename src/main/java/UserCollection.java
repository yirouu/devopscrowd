import java.io.PrintStream;
import java.util.*;

public class UserCollection {

	private ArrayList<User> users = new ArrayList<>();
	private List<String> UserEmails = new ArrayList<String>();
	private int capacity;

	public UserCollection() {
		/*
		 * songs.add(new Song("0001","good 4 u","Olivia Rodrigo",3.59)); songs.add(new
		 * Song("0002","Peaches","Justin Bieber",3.18)); songs.add(new
		 * Song("0003","MONTERO","Lil Nas X",2.3)); songs.add(new
		 * Song("0004","bad guy","Billie Eilish",3.14));
		 */

		this.capacity = 20;
	}

	public UserCollection(int capacity) {
		this.capacity = capacity;
	}

	public List<User> getUser() {
		return users;
	}

	public void addUser(User user) {
		if (users.size() != capacity) {
			users.add(user);
		}
	}

	public void regUser(User user) {
		for (User s : users) {
			String emails = s.getEmail();
			UserEmails.add(emails);
		}

		if (UserEmails.contains(user.email)) {
			System.out.println("Email Existed");
		} else {
			users.add(user);

		}

	}

	public User loginUser(String email, String password) {

		for (User s : users) {
			if (s.getEmail().equals(email) && s.getPassword().equals(password))
				return s;
		}
		return null;
	}

	public User findUserById(int id) {
		for (User s : users) {
			Integer userid = s.getUserid();
			boolean isEqual = userid.equals(id);
			if (isEqual)
				return s;
		}
		return null;
	}

	public User updateUser(int id, String username, String email, String password, String address, int postal) {

		if (!findUserById(id).equals(null)) {
			for (User s : users) {
				s.setUsername(username);
				s.setEmail(email);
				s.setPassword(password);
				s.setRole("user");
				s.setAddress(address);
				s.setPostal(postal);

				return s;
			}
		}
		return null;
	}

	public User deleteUser(int id) {

		if (!findUserById(id).equals(null)) {
			users.remove(id);
		}
		return null;
	}
}
