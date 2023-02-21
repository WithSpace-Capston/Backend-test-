package withSpace_test2.withSpace_test2;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import withSpace_test2.withSpace_test2.repository.MemberRepository;
import withSpace_test2.withSpace_test2.repository.SpaceRepository;
import withSpace_test2.withSpace_test2.repository.TeamRepository;
import withSpace_test2.withSpace_test2.service.MemberService;
import withSpace_test2.withSpace_test2.service.SpaceService;
import withSpace_test2.withSpace_test2.service.TeamService;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {


    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final SpaceRepository spaceRepository;

//    @Autowired
//    public SpringConfig(MemberRepository memberRepository,
//                        TeamRepository teamRepository)
//    {
//        this.memberRepository = memberRepository;
//        this.teamRepository = teamRepository;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public SpaceService spaceService() {
        return new SpaceService(spaceRepository);
    }


}
