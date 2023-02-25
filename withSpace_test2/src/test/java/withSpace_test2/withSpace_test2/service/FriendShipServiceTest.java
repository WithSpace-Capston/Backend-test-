package withSpace_test2.withSpace_test2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.friend.FriendShip;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class FriendShipServiceTest {

    @Autowired
    FriendShipService friendShipService;

    @Autowired
    MemberService memberService;

    @Test
    public void 친구_신청() {
        Member memberA = new Member();
        memberA.setMemberName("memberA");

        Member memberB = new Member();
        memberB.setMemberName("memberB");

        memberService.join(memberA);
        memberService.join(memberB);
        FriendShip friendShipA = new FriendShip(memberA, memberB);
        FriendShip friendShipB = new FriendShip(memberB, memberA);


        Long saveFriendShipMemberA = friendShipService.addFriend(friendShipA);
        Long saveFriendShipMemberB = friendShipService.addFriend(friendShipB);

        assertThat(saveFriendShipMemberA).isEqualTo(friendShipA.getId());
        assertThat(saveFriendShipMemberB).isEqualTo(friendShipB.getId());
    }

}