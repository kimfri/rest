package messenger.database;

import java.util.HashMap;
import java.util.Map;
import messenger.model.Message;

/**
 *
 * @author kimfr
 */
public class DatabaseClass {

  private static Map<Integer, Message> messages = new HashMap<>();

  public static Map<Integer, Message> getMessages() {
    return messages;
  }

}
