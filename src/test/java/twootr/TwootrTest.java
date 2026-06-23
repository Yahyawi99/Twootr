package twootr;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class TwootrTest {

  final Twootr twooter = new Twootr();
  final ReceiverEndPoint receiverEndPoint = new ReceiverEndPoint();

  @Test
  public void shouldBeAbleToAuthenticateUser() {
    final Optional<SenderEndPoint> endPoint = twooter.onLogon(TestData.USER_ID, "correct password", receiverEndPoint);

    assertTrue(endPoint.isPresent());

  }

  @Test
  public void shouldNotAuthenticateUserWithWrongPassword() {

    final Optional<SenderEndPoint> endPoint = twooter.onLogon(TestData.USER_ID, "bad password", receiverEndPoint);

    assertFalse(endPoint.isPresent());
  }

  @Test
  public void shouldFollowValidUser() {
    // logon();

    final FollowStatus followStatus = twooter.onFollow(TestData.OTHER_USER_ID);

    assertEquals(FollowStatus.SUCCESS, followStatus);
  }

  @Test
  public void shouldNotDuplicateFollowValidUser() {
    final FollowStatus followStatus = twooter.onFollow(TestData.OTHER_USER_ID);

    assertEquals(FollowStatus.ALREADY_FOLLOWING, followStatus);
  }

  @Test
  public void shouldNotFollowInValidUser() {
    final FollowStatus followStatus = twooter.onFollow(TestData.INVALID_USER_ID);

    assertEquals(FollowStatus.INVALID_USER, followStatus);
  }
}
