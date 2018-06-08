package si.um.feri.praktikum.connection;

public class ConnectionSettings {

	private String serverIp;
	private int serverPort;
	private String userName;
	private String password;

	public ConnectionSettings() {
		this(null, 0, null, null);
	}

	public ConnectionSettings(String serverIp, int serverPort) {
		this(serverIp, serverPort, null, null);
	}

	public ConnectionSettings(String serverIp, int serverPort, String userName, String password) {
		this.serverIp = serverIp;
		this.serverPort = serverPort;
		this.userName = userName;
		this.password = password;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}