//package withSpace_test2.withSpace_test2.service;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//import withSpace_test2.withSpace_test2.domain.Member;
//import withSpace_test2.withSpace_test2.repository.MemberRepository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@Transactional
//@SpringBootTest
//class MemberServiceTest {
//
//    @Autowired
//    MemberService memberService;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    @Rollback(false)
//    public void 회원가입() throws Exception {
//        //given
//        Member member = new Member();
//        member.setMemberName("한슬이");
//        //when
//                    //Long id = memberService.join(member);
//        //then
//                    //Member findMember = memberService.findOne(id).get();
//            //assertThat(member.getMemberName()).isEqualTo(findMember.getMemberName());
//    }
//
//
//}