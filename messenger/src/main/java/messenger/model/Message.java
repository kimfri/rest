package messenger.model;

import javax.xml.bind.annotation.XmlRootElement;
import org.bson.Document;

/**
 *
 * @author kimfr
 */
@XmlRootElement
public class Message {
  private int id;
  private String message;
  private String author;

  public Message() {
  }

  public Message(int id, String message, String author) {
    this.id = id;
    this.message = message;
    this.author = author;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public String toString() {
    return "Message{" + "id=" + id + ", message=" + message + ", author=" + author + '}';
  }
  

  public Document getDocument() {
    return new Document().append("id", getId())
            .append("message", getMessage())
            .append("author", getAuthor());
  }
  
  public static Message getMessageFromDocument(Document document) {
    return new Message(document.getInteger("id"), 
            document.getString("message"),
            document.getString("author"));
  }
  
}
