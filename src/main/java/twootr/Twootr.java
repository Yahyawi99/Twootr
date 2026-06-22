package twootr;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Twootr {
  private final Map<String, User> users = new HashMap<>();

  public Twootr() {
  }

  Optional<SenderEndPoint> onLogon(String userId, String pwd, ReceiverEndPoint receiver) {
    return Optional.empty();
  }
}
