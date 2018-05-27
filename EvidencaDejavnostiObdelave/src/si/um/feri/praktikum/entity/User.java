package si.um.feri.praktikum.entity;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	// USER THAT WILL USER OUR PORTAL
	private String firstName;
	private String lastName;
	private int mobileNumber;
	private String email;
	private int id;
<<<<<<< HEAD
	private String password;
	
	
	
	public User() 	{
		
	}
	
	public User(String firstName, String lastName,int mobileNumber, String email,String password) {
=======

	// Private and public key so user can sign evidences to know which evidence
	// belong to which user
	private PrivateKey privateKey;
	private PublicKey publicKey;

	public User() {

	}

	public User(String firstName, String lastName, int mobileNum, String email) {
>>>>>>> 85e8f3934aec2d2a5491a13eb474fc075b3093b4
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
<<<<<<< HEAD
		this.password = password;
		
		
=======

>>>>>>> 85e8f3934aec2d2a5491a13eb474fc075b3093b4
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

<<<<<<< HEAD
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
=======
	public void generateKeyPair() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(1024, random);
			KeyPair pair = keyGen.generateKeyPair();
			privateKey = pair.getPrivate();
			publicKey = pair.getPublic();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
>>>>>>> 85e8f3934aec2d2a5491a13eb474fc075b3093b4

}
