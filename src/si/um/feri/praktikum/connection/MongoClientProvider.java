package si.um.feri.praktikum.connection;




import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;







public class MongoClientProvider {

	private static MongoClientProvider instance  = new MongoClientProvider();


	//singleton for creating mongodb connection
	private MongoClientProvider() {}

	private MongoClient mongo = null;
	
	
	//morphia???
	
	public MongoClient getMongo() {
		if(mongo==null) {
			MongoClientOptions.Builder options = MongoClientOptions.builder()
					.connectionsPerHost(3).
					maxConnectionIdleTime((60 * 1_000))
					.maxConnectionLifeTime((120 * 1_000));
		}
		String serverName = "localhost";
		int serverPort = 27017;
		
		try {
			mongo = new MongoClient(serverName,serverPort);
		}catch(Exception e) {
			
		}
	

	return mongo;
}
	
	
	
	public void close() {
		if (mongo != null) {
			try {
				mongo.close();
				mongo = null;
			}catch(Exception e) {
				
			}
		
	}



}
	public static MongoClientProvider getInstance() {
		return instance;
	}

}