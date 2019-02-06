package messenger.database;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.HashMap;

/**
 *
 * @author kimfr
 */
public class MongoConnectionHandler {
  public static MongoConnectionHandler mongoConnectionHandler;

  private MongoClient mongoClient;
  private MongoDatabase mongoDatabase = null;
  private HashMap<String, MongoCollection> mongoCollections = new HashMap<>();
  
  private final String dbIpAddress = "127.0.0.1";
  private final int dbPort = 27017;
  private final String dbName = "test";
  
  
  public MongoConnectionHandler() {
    ConnectionString cs = new ConnectionString("mongodb://" + dbIpAddress + ":" + dbPort);
    mongoClient = MongoClients.create(cs);
    mongoDatabase = mongoClient.getDatabase(dbName);
  }
  
  
  public static MongoConnectionHandler getInstance() {
    if(mongoConnectionHandler == null) {
      mongoConnectionHandler = new MongoConnectionHandler();
    }
    return mongoConnectionHandler;
  }
  
  public MongoCollection getMongoCollection(String collectionName) {
    if (mongoCollections.containsKey(collectionName)) {
      return mongoCollections.get(collectionName);
    }
    MongoCollection mc = this.mongoDatabase.getCollection(collectionName);
    this.mongoCollections.put(collectionName, mc);
    return mc;
  }
  
}
