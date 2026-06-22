package twootr;

import java.util.Map;
import java.util.Optional;

public class Twootr {
  private final Map<String, User> users;

  public Twootr() {
    this.users = null;
  }

  Optional<SenderEndPoint> onLogon(String userId, String pwd, ReceiverEndPoint receiver);
}
