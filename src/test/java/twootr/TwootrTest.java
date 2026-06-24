package twootr;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class TwootrTest {

  private Twootr twootr;
  private SenderEndPoint endPoint;
  private ReceiverEndPoint receiverEndPoint = mock(ReceiverEndPoint.class);

  @Test
  public void shouldBeAbleToAuthenticateUser() {
    logon();
  }

  @Test
  public void shouldNotAuthenticateUserWithWrongPassword() {

    final Optional<SenderEndPoint> endPoint = twootr.onLogon(TestData.USER_ID, "bad password", receiverEndPoint);

    assertFalse(endPoint.isPresent());
  }

  @Test
  public void shouldFollowValidUser() {
    logon();

    final FollowStatus followStatus = endPoint.onFollow(TestData.OTHER_USER_ID);

    assertEquals(FollowStatus.SUCCESS, followStatus);
  }

  @Test
  public void shouldNotDuplicateFollowValidUser() {
    final FollowStatus followStatus = endPoint.onFollow(TestData.OTHER_USER_ID);

    assertEquals(FollowStatus.ALREADY_FOLLOWING, followStatus);
  }

  @Test
  public void shouldNotFollowInValidUser() {
    final FollowStatus followStatus = endPoint.onFollow(TestData.INVALID_USER_ID);

    assertEquals(FollowStatus.INVALID_USER, followStatus);
  }

  @Test
  public void shouldReceiveTwootsFromFollowedUser() {
    final String id = "1";

    logon();

    endPoint.onFollow(TestData.OTHER_USER_ID);

    final SenderEndPoint otherEndPoint = otherLogon();
    otherEndPoint.onSendTwoot(id, TWOOT);

    verify(twootRepository).add(id, TestData.OTHER_USER_ID, TWOOT);
    verify(receiverEndPoint).onTwoot(new Twoot(id, TestData.OTHER_USER_ID, TWOOT, new Position(0)));
  }

  public void shouldReceiveReplayOfTwootsAfterLogoff() {
    final String id = -1;

    userFollowsOtherUser();

    final SenderEndPoint otherEndPoint = otherLogon();
    otherEndPoint.onSendTwoot("id", mock(User.class), "id");

    logon();

    verify(receiverEndPoint).onTwoot(twootAt);

  }

  // =================================
  // Refactoring
  private void logon() {
    this.endPoint = logon(TestData.USER_ID, receiverEndPoint);
  }

  private SenderEndPoint logon(final String userId, final ReceiverEndPoint receiverEndPoint) {
    final Optional<SenderEndPoint> endPoint = twootr.onLogon(userId, TestData.PASSWORD, receiverEndPoint);

    assertTrue(endPoint.isPresent(), "Failed to logon");
    return endPoint.get();
  }

}
