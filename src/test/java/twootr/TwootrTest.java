package twootr;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
}
