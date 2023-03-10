package withSpace_test2.withSpace_test2.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.*;
import withSpace_test2.withSpace_test2.domain.space.TeamSpace;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;
import withSpace_test2.withSpace_test2.repository.MemberTeamRepository;
import withSpace_test2.withSpace_test2.repository.ScheduleRepository;
import withSpace_test2.withSpace_test2.repository.SpaceRepository;
import withSpace_test2.withSpace_test2.repository.TeamRepository;
import withSpace_test2.withSpace_test2.responsedto.schedule.ScheduleDto;

import javax.swing.text.Style;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private final MemberTeamRepository memberTeamRepository;
    private final SpaceRepository spaceRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Long makeTeam(Member member, String teamName) { //팀 생성 - 팀을 생성하는 회원에게는 바로 팀 부여

        Team team = new Team(teamName);

        teamRepository.save(team);

        makeMemberTeamRelation(member, team); //멤버팀 연관관계 생성

        //팀 생성시 스페이스 생성 + 부여
        TeamSpace teamSpace = new TeamSpace(team);
        team.setTeamSpace(teamSpace);
        spaceRepository.save(teamSpace);

        //스페이스 생성했으니 바로 스케줄도 만들어서 줌..
        Schedule schedule = new Schedule(teamSpace);
        scheduleRepository.save(schedule);

        System.out.println(team.getMemberCount()+"============================================================================");


        return team.getId();
    }

    @Transactional
    public Long join(Member member, Long teamId) { //팀 가입
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        Team team = teamOptional.orElseThrow(()
                -> new EntityNotFoundException("팀이 없음 - teamService.join / teamId: " + teamId));


        MemberTeamId memberTeamId = new MemberTeamId(member.getId(), teamId);
        Optional<MemberTeam> memberTeamOptional = memberTeamRepository.findById(memberTeamId);
        MemberTeam memberTeam = memberTeamOptional.orElse(null);

        if (memberTeam == null) { //이미 가입되어있는 경우
            makeMemberTeamRelation(member, team);
        }

        System.out.println(team.getMemberCount()+"============================================================================");

        return teamId;
    }

    public void makeMemberTeamRelation(Member member, Team team) {

        // 멤버-팀 관계 생성
        MemberTeam memberTeam = new MemberTeam(member, team);
        memberTeamRepository.save(memberTeam);

        // 멤버 - 멤버팀 - 팀 이어주기
        member.getMemberTeams().add(memberTeam);
        team.getMemberTeams().add(memberTeam);

        teamRepository.incrementMemberCount(team.getId());
    }

    public Optional<Team> findOne(Long temaId) {
        return teamRepository.findById(temaId);
    }

    @Transactional
    public void deleteTeam(Long teamId) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        Team team = teamOptional.orElseThrow(()
                -> new EntityNotFoundException("팀이 없음 - teamService.deleteTeam / teamId: " + teamId));

        teamRepository.delete(team);
        memberTeamRepository.deleteByTeamId(teamId);
    }

}
