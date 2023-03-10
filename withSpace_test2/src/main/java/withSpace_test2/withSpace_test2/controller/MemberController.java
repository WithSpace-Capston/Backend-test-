package withSpace_test2.withSpace_test2.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.requestdto.member.MemberJoinRequestDto;
import withSpace_test2.withSpace_test2.requestdto.member.MemberUpdateRequestDto;
import withSpace_test2.withSpace_test2.responsedto.space.MemberSpaceDto;
import withSpace_test2.withSpace_test2.responsedto.BasicResponse;
import withSpace_test2.withSpace_test2.responsedto.member.JoinMemberResponse;
import withSpace_test2.withSpace_test2.responsedto.member.MemberBasicResponse;
import withSpace_test2.withSpace_test2.responsedto.member.GetMemberResponseDto;
import withSpace_test2.withSpace_test2.responsedto.member.UpdateMemberResponse;
import withSpace_test2.withSpace_test2.service.MemberService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private static final int SUCCESS = 200;
    private static final int CREATED = 201;

    private final MemberService memberService;

    @PostMapping("/member") //회원가입
    public ResponseEntity<JoinMemberResponse> joinMember(@RequestBody MemberJoinRequestDto memberJoinRequestDto) {
        Long memberId = memberService.join(memberJoinRequestDto);
        JoinMemberResponse joinMemberResponse = new JoinMemberResponse(memberId, CREATED, "회원가입 완료");
        return new ResponseEntity<>(joinMemberResponse, HttpStatus.CREATED);
    }


    @GetMapping("/member/{memberId}") //회원 단건 조회
    public ResponseEntity<BasicResponse> getMember(@PathVariable("memberId") Long memberId) {
        Optional<Member> memberOptional = memberService.findOne(memberId);

        Member member = memberOptional.orElseThrow(() -> new EntityNotFoundException("회원 조회 실패"));

        GetMemberResponseDto getMemberResponseDto = new GetMemberResponseDto(member);
        BasicResponse basicResponse = new BasicResponse<>(1, "회원 조회 성공", getMemberResponseDto);
        return new ResponseEntity<>(basicResponse, HttpStatus.OK);

    }

    @GetMapping("/member/{memberId}/space") //회원 스페이스 조회
    public ResponseEntity<BasicResponse> getMemberSpace(@PathVariable("memberId") Long memberId) {
        Optional<Member> memberOptional = memberService.findOne(memberId);
        Member member = memberOptional.orElseThrow(() -> new EntityNotFoundException("회원 조회 실패"));

        MemberSpaceDto memberSpaceDto = new MemberSpaceDto(member);
        BasicResponse basicResponse = new BasicResponse<>(1, "스페이스 조회 성공", memberSpaceDto);
        return new ResponseEntity<>(basicResponse, HttpStatus.OK);
    }

    @DeleteMapping("/member/{memberId}") // 회원 삭제
    public ResponseEntity<MemberBasicResponse> deleteMember(@PathVariable Long memberId) {
        memberService.delete(memberId);
        MemberBasicResponse memberBasicResponse = new MemberBasicResponse(SUCCESS, "회원 삭제가 정상적으로 되었습니다.");
        return new ResponseEntity<>(memberBasicResponse, HttpStatus.OK);

    }

    @PatchMapping("/member/{memberId}") // 회원 업데이트
    public ResponseEntity<UpdateMemberResponse> updateMember(@PathVariable Long memberId, @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        Long updatedMemberId = memberService.update(memberId, memberUpdateRequestDto);
        UpdateMemberResponse updateMemberResponse = new UpdateMemberResponse(updatedMemberId, SUCCESS, "멤버 업데이트 완료");
        return new ResponseEntity<>(updateMemberResponse, HttpStatus.OK);
    }

}
