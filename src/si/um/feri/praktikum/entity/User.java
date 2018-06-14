package si.um.feri.praktikum.entity;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import si.um.feri.praktikum.util.StringUtil;

/**
 * 
 * Model for users, it gets filled from form or service json (Facebook, Google,
 * ...).
 *
 */
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

	/**
	 * Empty constructor.
	 */
	public User() {
		this("", "", 0, "", "");
	}

	/**
	 * Full constructor.<br/>
	 * 
	 * @param firstName
	 *            User first name.
	 * @param lastName
	 *            User last name.
	 * @param mobileNumber
	 *            User mobile number.
	 * @param email
	 *            User email address.
	 * @param password
	 *            User password.
	 */
	public User(String firstName, String lastName, int mobileNumber, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		generateKeyPair();
	}

	/**
	 * 
	 * @param email
	 * @param password
	 */
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	/**
	 * 
	 * @param email
	 */
	public User(String email) {
		this.email = email;
	}

	// Generates id from input string and hashes it.
	private String generateId(String input) {
		return StringUtil.hashSHA256(input);
	}

	/**
	 * @return Gets user id.
	 */
	public String getId() {
		if (this.id == null)
			this.id = generateId(this.email + this.password);
		return this.id;
	}

	/**
	 * @param id
	 *            Sets user id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Gets user first name.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            User first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return User last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            User last name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return User mobile number.
	 */
	public int getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *            User mobile number.
	 */
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return User email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            User email address.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return User password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            User password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Generates user key pair used for signing data, but we aren't using it yet.
	 */
	// TODO: In future use this and sign data.
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
