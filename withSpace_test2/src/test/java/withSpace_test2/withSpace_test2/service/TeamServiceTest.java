package withSpace_test2.withSpace_test2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.Team;
import withSpace_test2.withSpace_test2.repository.TeamRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class TeamServiceTest {

    @Autowired
    TeamService teamService;

    @Autowired
    TeamRepository teamRepository;

    @Test
    @Rollback(false)
    public void 팀생성() throws Exception {
        //given

        //회원 생성
        Member member = new Member();
        member.setMemberName("팀생성예정회원님");

        //팀 생성
        Team team = new Team();
        team.setTeamName("팀이름입니다");

        //when
        Long teamId = teamService.makeTeam(member, team);

        //then
        Team findTeam = teamService.findOne(teamId).get();
        assertThat(team.getId()).isEqualTo(findTeam.getId());
        assertThat(team.getTeamName()).isEqualTo(findTeam.getTeamName());

    }

    @Test
    @Rollback(false)
    public void 팀가입() throws Exception {
        //given

        //회원 생성
        Member member = new Member();
        member.setMemberName("팀가입예정회원님");

        //팀 생성
        Team team = new Team();
        team.setTeamName("팀이름입니다");
        Long teamId = teamService.makeTeam(member, team);

        //when
        Long joinId = teamService.join(member, teamId); //가입

        //then
        Team findTeam = teamService.findOne(joinId).get();
        assertThat(team.getId()).isEqualTo(findTeam.getId());
        assertThat(team.getTeamName()).isEqualTo(findTeam.getTeamName());
    }


}