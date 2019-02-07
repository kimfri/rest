package messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import messenger.database.DatabaseClass;
import messenger.database.MongoConnectionHandler;
import messenger.model.Message;
import org.bson.Document;

/**
 *
 * @author kimfr
 */
public class MessageService {

  private final static String COLLECTION = "messages";

  private Map<Integer, Message> messages = DatabaseClass.getMessages();
  private MongoConnectionHandler mch = new MongoConnectionHandler();
  

  public MessageService() {
//		messages.put(1, new Message(1, "Hello World", "kimfri")); 
//		messages.put(2, new Message(2, "Hello Jersey", "kimfri"));
//    mch.getMongoCollection(COLLECTION).insertOne((new Message(1, "Hello !", "kim")).getDocument());
//    mch.getMongoCollection(COLLECTION).insertOne((new Message(2, "Hello!", "Wille")).getDocument());
   
  }

  public List<Message> getAllMessages() {
//		return new ArrayList<Message>(messages.values()); 
    Document query = new Document();
    List<Document> docs = (List<Document>) mch
            .getMongoCollection(COLLECTION)
            .find(query)
            .into(new ArrayList<Document>());
    List<Message> messages = new ArrayList<>();
    for (Document doc : docs) {
      messages.add(Message.getMessageFromDocument(doc));
    }
    return messages;
  }

  public Message getMessage(long id) {
//    return messages.get(id);
    Document query = new Document().append("id", id);
    List<Document> docs = (List<Document>) mch
            .getMongoCollection(COLLECTION)
            .find(query)
            .into(new ArrayList<Document>());
    if (docs.size() == 0) {
      return null;
    }
    List<Message> messages = new ArrayList<>();
    for (Document doc : docs) {
      messages.add(Message.getMessageFromDocument(doc));
    }
    Message m = messages.get(0);
    return messages.get(0);
  }

  public Message addMessage(Message message) {
    message.setId(messages.size() + 1);
    messages.put(message.getId(), message);
    return message;
  }

  public Message updateMessage(Message message) {
    if (message.getId() <= 0) {
      return null;
    }
    messages.put(message.getId(), message);
    return message;
  }

  public Message removeMessage(long id) {
    return messages.remove(id);
  }
}
