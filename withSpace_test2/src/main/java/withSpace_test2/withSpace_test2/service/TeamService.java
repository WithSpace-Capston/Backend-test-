package withSpace_test2.withSpace_test2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.MemberTeam;
import withSpace_test2.withSpace_test2.domain.Team;
import withSpace_test2.withSpace_test2.repository.TeamRepository;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public Long makeTeam(Member member, Team team) { //팀 생성 - 팀을 생성하는 회원에게는 바로 팀 부여

        teamRepository.save(team);

        makeMemberTeamRelation(member, team);

        return team.getId();
    }

    public Long join(Member member, Long teamId) { //팀 가입
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        Team team = teamOptional.orElse(null);

        if (team == null) {
            // 팀이 존재하지 않는 경우
        }
        makeMemberTeamRelation(member, team);

        return teamId;
    }

    public void makeMemberTeamRelation(Member member, Team team) {
        // 멤버-팀 관계 생성
        MemberTeam memberTeam = new MemberTeam(member, team);

        // 멤버 - 멤버팀 - 팀 이어주기
        member.getMemberTeams().add(memberTeam);
        team.getMemberTeams().add(memberTeam);
    }

    public Optional<Team> findOne(Long temaId) {
        return teamRepository.findById(temaId);
    }

}
