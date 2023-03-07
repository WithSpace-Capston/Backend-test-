package withSpace_test2.withSpace_test2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.MemberTeam;
import withSpace_test2.withSpace_test2.domain.Team;
import withSpace_test2.withSpace_test2.repository.MemberTeamRepository;
import withSpace_test2.withSpace_test2.repository.TeamRepository;

import javax.swing.text.Style;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private final MemberTeamRepository memberTeamRepository;

    @Transactional
    public Long makeTeam(Member member, String teamName) { //팀 생성 - 팀을 생성하는 회원에게는 바로 팀 부여

        Team team = new Team(teamName);

        teamRepository.save(team);

        makeMemberTeamRelation(member, team); //멤버팀 연관관계 생성

        return team.getId();
    }

    @Transactional
    public Long join(Member member, Long teamId) { //팀 가입
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        Team team = teamOptional.orElse(null);

        if (team == null) {
            // 팀이 존재하지 않는 경우
        }
        int teamCount = team.getMemberCount() + 1;
        team.setMemberCount(teamCount); //setter말고 더 좋은방법 있을라나,,?
        makeMemberTeamRelation(member, team);

        System.out.println("==================================================================================");
        System.out.println(teamId);
        System.out.println(team.getMemberCount());

        return teamId;
    }

    public void makeMemberTeamRelation(Member member, Team team) {
        // 멤버-팀 관계 생성
        MemberTeam memberTeam = new MemberTeam(member, team);
        memberTeamRepository.save(memberTeam);

        // 멤버 - 멤버팀 - 팀 이어주기
        member.getMemberTeams().add(memberTeam);
        team.getMemberTeams().add(memberTeam);
    }

    public Optional<Team> findOne(Long temaId) {
        return teamRepository.findById(temaId);
    }

}
