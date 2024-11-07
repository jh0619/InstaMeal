/**
 * Created by Shiang Jin, Chin
 */
package instameal.model;

/**
 * Users is a simple, plain old java objects (POJO).
 */
public class Users {
	protected int userId;
	protected String userName;
	protected String passWord;
	protected String firstName;
	protected String lastName;
	protected String email;


	public Users(String userName, String password, String firstName, String lastName, String email) {
		this.userName = userName;
		this.passWord = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public Users(int userId, String userName, String password, String firstName, String lastName, String email) {
		this.userId = userId;
		this.userName = userName;
		this.passWord = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Users(String userName) {
		this.userName = userName;
	}

	/** Getters and setters. */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String password) {
		this.passWord = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "Users{" +
				"userId='" + userId + '\'' +
				"userName='" + userName + '\'' +
				", password='" + passWord + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
