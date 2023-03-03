package withSpace_test2.withSpace_test2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.friend.FriendShip;
import withSpace_test2.withSpace_test2.domain.friend.FriendStatus;
import withSpace_test2.withSpace_test2.repository.FriendShipRepository;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class FriendShipService {

    private final FriendShipRepository friendShipRepository;

    @Transactional
    public Long addFriend(FriendShip friendShip) {
        FriendShip saveFriendRequest = friendShipRepository.save(friendShip);
        ValidateFriendShip(friendShip);
        return saveFriendRequest.getId();
    }

    //친구관계를 맺었는지 확인하는 함수
    public void ValidateFriendShip(FriendShip friendShip) {
        Optional<FriendShip> findFriendShip = friendShipRepository.findFriendByMemberId(friendShip.getMember().getId());
        if (!findFriendShip.isEmpty()) {
            findFriendShip.get().setStatus(FriendStatus.ACCEPTED);
            friendShip.setStatus(FriendStatus.ACCEPTED);
        } else {
            friendShip.setStatus(FriendStatus.PENDING);
        }
    }

    public List<Member> findFriendList(Member member) {
        List<Member> friendList = friendShipRepository.findFriendListByMemberId(member.getId(), FriendStatus.ACCEPTED);
        return friendList;
    }
}
