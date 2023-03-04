package withSpace_test2.withSpace_test2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.friend.FriendShip;
import withSpace_test2.withSpace_test2.requestdto.friendship.FriendRequestDto;
import withSpace_test2.withSpace_test2.responsedto.BasicResponse;
import withSpace_test2.withSpace_test2.responsedto.friend.FriendDto;
import withSpace_test2.withSpace_test2.responsedto.friend.SendFriendShipResponseDto;
import withSpace_test2.withSpace_test2.service.FriendShipService;
import withSpace_test2.withSpace_test2.service.MemberService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FriendShipController {
    private static final int CREATED = 201;


    private final FriendShipService friendShipService;
    private final MemberService memberService;

    @GetMapping("/{memberId}/friend")
    public ResponseEntity<BasicResponse> friend(@PathVariable("memberId") Long memberId) {
        Optional<Member> member = memberService.findOne(memberId);
        List<Member> friendList = friendShipService.findFriendList(member.get());
        List<FriendDto> collect = friendList.stream()
                .map(f -> new FriendDto(f))
                .collect(Collectors.toList());
        BasicResponse basicResponse = new BasicResponse<>(collect.size(), "친구목록 요청 성공", collect);
        return new ResponseEntity<>(basicResponse, HttpStatus.OK);
    }

    @PostMapping("/friend")
    public ResponseEntity<SendFriendShipResponseDto> sendFriendShip(@RequestBody FriendRequestDto friendRequestDto) {
        //친구 요청 보낸 사람
        Optional<Member> friendRequester = memberService.findOne(friendRequestDto.getId());
        //친구 요청 받은 사람
        Optional<Member> friendRequestee = memberService.findOne(friendRequestDto.getFriendId());

        FriendShip friendShip = new FriendShip(friendRequester.get(), friendRequestee.get());

        friendShipService.addFriend(friendShip);

        SendFriendShipResponseDto friendResponseDto = new SendFriendShipResponseDto(friendRequester.get().getId(), CREATED, "친구신청을 보냈습니다.");
        return new ResponseEntity<>(friendResponseDto, HttpStatus.CREATED);
    }
}
