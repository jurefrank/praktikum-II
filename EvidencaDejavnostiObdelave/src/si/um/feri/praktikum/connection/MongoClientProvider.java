package si.um.feri.praktikum.connection;




import com.mongodb.MongoClient;


import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;


@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class MongoClientProvider {

private MongoClient mongoClient = null;

@Lock(LockType.READ)
public MongoClient getMongoClient(){
return mongoClient;
}

@PostConstruct
public void init() {

mongoClient = new MongoClient("localhost", 27017);
}

}	