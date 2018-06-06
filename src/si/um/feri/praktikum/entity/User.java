package si.um.feri.praktikum.entity;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import si.um.feri.praktikum.util.StringUtil;

@SuppressWarnings("unused")
public class User {

	// USER THAT WILL USER OUR PORTAL
	private String firstName;
	private String lastName;
	private int mobileNumber;
	private String email;
	private String id = null;
	private String password;

	// Private and public key so user can sign evidences to know which evidence
	// belong to which user
	private PrivateKey privateKey;

	private PublicKey publicKey;

	public User() {
		this("", "", 0, "", "");
	}

	public User(String firstName, String lastName, int mobileNumber, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		generateKeyPair();
	}
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	private String generateId(String input) {
		return StringUtil.hashSHA256(input);
	}

	public String getId() {
		if (this.id == null)
			this.id = generateId(this.email + this.password);
		return this.id;
	}

	public void setId(String id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

}
