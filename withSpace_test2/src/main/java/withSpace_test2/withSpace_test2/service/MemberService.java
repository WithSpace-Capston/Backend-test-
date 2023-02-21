package withSpace_test2.withSpace_test2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.Team;
import withSpace_test2.withSpace_test2.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor // final 필드 생성자 주입 코드 자동 생성
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) { //회원가입
        memberRepository.save(member);
        return member.getId();
    }


    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
