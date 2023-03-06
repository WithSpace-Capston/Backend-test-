package withSpace_test2.withSpace_test2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.Team;
import withSpace_test2.withSpace_test2.domain.space.MemberSpace;
import withSpace_test2.withSpace_test2.domain.space.Space;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;
import withSpace_test2.withSpace_test2.repository.MemberRepository;
import withSpace_test2.withSpace_test2.repository.ScheduleRepository;
import withSpace_test2.withSpace_test2.repository.SpaceRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final 필드 생성자 주입 코드 자동 생성
public class MemberService {

    private final MemberRepository memberRepository;
    private final SpaceRepository spaceRepository;
    private final ScheduleRepository scheduleRepository;
    //private final SpaceService spaceService;

    @Transactional
    public Long join(String memberName, String email, String password) { //회원가입
        Member member = new Member(memberName, email, password);
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
}
