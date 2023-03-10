package withSpace_test2.withSpace_test2.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.space.MemberSpace;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;
import withSpace_test2.withSpace_test2.repository.MemberRepository;
import withSpace_test2.withSpace_test2.repository.ScheduleRepository;
import withSpace_test2.withSpace_test2.repository.SpaceRepository;
import withSpace_test2.withSpace_test2.requestdto.member.MemberJoinRequestDto;
import withSpace_test2.withSpace_test2.requestdto.member.MemberUpdateRequestDto;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final 필드 생성자 주입 코드 자동 생성
public class MemberService {

    private final MemberRepository memberRepository;
    private final SpaceRepository spaceRepository;
    private final ScheduleRepository scheduleRepository;
    //private final SpaceService spaceService;

//    @Transactional
//    public Long join(String memberName, String email, String password) { //회원가입
//        Member member = new Member(memberName, email, password);
//        memberRepository.save(member);
//
//        //회원가입시 스페이스 생성 + 부여
//        MemberSpace memberSpace = new MemberSpace();
//            //spaceService.assignSpace(member);
//        member.setMemberSpace(memberSpace);
//        spaceRepository.save(memberSpace);
//
//        //스페이스 생성했으니 바로 스케줄도 만들어서 줌..
//        Schedule schedule = new Schedule(memberSpace);
//        scheduleRepository.save(schedule);
//
//        return member.getId();
//    }

    @Transactional
    public Long join(MemberJoinRequestDto joinRequestDto) { //회원가입
        Member member = new Member(joinRequestDto.getMemberName(),joinRequestDto.getEmail(),joinRequestDto.getPassword());
        memberRepository.save(member);

        //회원가입시 스페이스 생성 + 부여
        MemberSpace memberSpace = new MemberSpace();
        //spaceService.assignSpace(member);
        member.setMemberSpace(memberSpace);
        spaceRepository.save(memberSpace);

        //스페이스 생성했으니 바로 스케줄도 만들어서 줌..
        Schedule schedule = new Schedule(memberSpace);
        scheduleRepository.save(schedule);

        return member.getId();
    }


    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

//    public Optional<Member> findOneWithMemberTeams(Long memberId){
//        return memberRepository.findByIdWithMemberTeams(memberId);
//    }

    @Transactional
    public void delete(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            memberRepository.delete(member);
        } else {
            throw new RuntimeException("Member not found for id: " + memberId);
        }
    }

    @Transactional
    public Long update(Long memberId, MemberUpdateRequestDto memberUpdateRequestDto) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("회원이 없습니다"));

        // Update member properties based on the DTO values
        if (memberUpdateRequestDto.getEmail() != null) {
            member.setEmail(memberUpdateRequestDto.getEmail());
        }
        if (memberUpdateRequestDto.getPassword() != null) {
            member.setPassword(memberUpdateRequestDto.getPassword());
        }
        if (memberUpdateRequestDto.getMemberName() != null) {
            member.setMemberName(memberUpdateRequestDto.getMemberName());
        }

        // 업데이트
        memberRepository.save(member);
        return member.getId();
    }


}
