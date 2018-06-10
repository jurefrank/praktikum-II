package si.um.feri.praktikum.connection;

/**
 * 
 * This class is used to store connection settings for mongo which are read from
 * host file.
 *
 */
public class ConnectionSettings {

	private String serverIp;
	private int serverPort;
	private String userName;
	private String password;

	/**
	 * Empty constructor, sets variables to null except int is set to 0.
	 */
	public ConnectionSettings() {
		this(null, 0, null, null);
	}

	/**
	 * Look full constructor for details.
	 * 
	 * @param serverIp
	 * 
	 * @param serverPort
	 * 
	 */
	public ConnectionSettings(String serverIp, int serverPort) {
		this(serverIp, serverPort, null, null);
	}

	/**
	 * @param serverIp
	 *            String server ip can be typed for example: localhost, or normal
	 *            IPv4 for example: 127.0.0.1.
	 * @param serverPort
	 *            Has to be int, default port of mongodb is 27017.
	 * @param userName
	 *            If mongoDB is using users you have to specify username.
	 * @param password
	 *            If mongoDB is using users you have to specify password.
	 */
	public ConnectionSettings(String serverIp, int serverPort, String userName, String password) {
		this.serverIp = serverIp;
		this.serverPort = serverPort;
		this.userName = userName;
		this.password = password;
	}

	/**
	 * @return Server ip address.
	 */
	public String getServerIp() {
		return serverIp;
	}

	/**
	 * @param serverIp
	 *            MongoDB server ip address.
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	/**
	 * @return MongoDB server port.
	 */
	public int getServerPort() {
		return serverPort;
	}

	/**
	 * @param serverPort
	 *            MongoDB server port.
	 */
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @return Mongo username.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            Mongo username.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return Mongo password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            Mongo password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}