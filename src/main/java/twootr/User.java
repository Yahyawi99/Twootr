package twootr;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class User {
  private final String id;
  private final Boolean loggedOn = false;
  public final List<User> followers = new ArrayList();

  public User(final String id) {
    this.id = id;
  }

  public void receiveTwoot(final Twoot twoot) {

  }

  public String getId() {
    return id;
  }

  public Boolean isLoggedOn() {
    return loggedOn;
  }

  public Stream<User> getFollowers() {
    return followers.stream();
  }

}
