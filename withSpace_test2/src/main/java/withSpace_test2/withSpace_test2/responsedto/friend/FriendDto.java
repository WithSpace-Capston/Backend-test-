package withSpace_test2.withSpace_test2.responsedto.friend;

import lombok.Data;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.friend.FriendShip;

import java.util.List;

@Data
public class FriendDto {

    private Long id;
    private String name;

    private String email;

    private Boolean status;

    public FriendDto(Member friend) {
        id = friend.getId();
        name = friend.getMemberName();
        email = friend.getEmail();
        status = friend.getStatus();
    }

    public FriendDto(FriendShip friendShip) { // MemberDto에서 사용
        Member friend = friendShip.getFriend();
        id = friend.getId();
        name = friend.getMemberName();
        email = friend.getEmail();
    }
}
