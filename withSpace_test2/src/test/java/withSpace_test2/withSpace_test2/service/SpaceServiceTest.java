package withSpace_test2.withSpace_test2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.Team;
import withSpace_test2.withSpace_test2.domain.space.MemberSpace;
import withSpace_test2.withSpace_test2.domain.space.MemberSpaceId;
import withSpace_test2.withSpace_test2.domain.space.Space;
import withSpace_test2.withSpace_test2.domain.space.TeamSpace;
import withSpace_test2.withSpace_test2.repository.SpaceRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
class SpaceServiceTest {
    
    @Autowired
    SpaceService spaceService;
    
    @Autowired
    SpaceRepository spaceRepository;

    @Test
    @Rollback(false)
    public void 스페이스할당_멤버() throws Exception {
        //given
        Member member = new Member();

        //when
        Space space = spaceService.assignSpace(member);

        //then
        assertNotNull(member.getMemberSpace());
        assertTrue(space instanceof MemberSpace);
    }

    @Test
    @Rollback(false)
    public void 스페이스할당_팀() throws Exception {
        //given
        Team team = new Team();

        //when
        Space space = spaceService.assignSpace(team);


        //then
        assertNotNull(team.getTeamSpace());
        assertTrue(space instanceof TeamSpace);
    }


}