package si.um.feri.praktikum.entity;

public class User {
	
	
	//USER THAT WILL USER OUR PORTAL
	private String firstName;
	private String lastName;
	private int mobileNumber;
	private String email;
	
	
	public User() 	{
		
	}
	
	public User(String firstName, String lastName,int mobileNum, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNum;
		this.email = email;
		
		
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

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
