package withSpace_test2.withSpace_test2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.responsedto.BasicResponse;
import withSpace_test2.withSpace_test2.responsedto.friend.FriendDto;
import withSpace_test2.withSpace_test2.service.FriendShipService;
import withSpace_test2.withSpace_test2.service.MemberService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FriendShipController {

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
}
