package withSpace_test2.withSpace_test2.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.Team;
import withSpace_test2.withSpace_test2.requestdto.team.CreateTeamRequestDto;
import withSpace_test2.withSpace_test2.requestdto.team.JoinTeamRequestDto;
import withSpace_test2.withSpace_test2.responsedto.BasicResponse;
import withSpace_test2.withSpace_test2.responsedto.team.CreateTeamResponse;
import withSpace_test2.withSpace_test2.responsedto.team.TeamListDto;
import withSpace_test2.withSpace_test2.service.MemberService;
import withSpace_test2.withSpace_test2.service.TeamService;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private static final int SUCCESS = 200;
    private static final int CREATED = 201;
    private final TeamService teamService;
    private final MemberService memberService;

    @PostMapping("/team") //팀생성
    public ResponseEntity<CreateTeamResponse> createTeam(@RequestBody CreateTeamRequestDto teamRequest) {
        Optional<Member> memberOptional = memberService.findOne(teamRequest.getMemberId());
        Member member = memberOptional.orElseThrow(() -> new EntityNotFoundException("회원 조회 실패"));

        Long teamId = teamService.makeTeam(member, teamRequest.getTeamName());
        CreateTeamResponse createTeamResponse = new CreateTeamResponse(teamId, CREATED, "팀 생성 완료");
        return new ResponseEntity<>(createTeamResponse, HttpStatus.CREATED);
    }

    @PostMapping("/team/{teamId}/members") //팀 가입
    public ResponseEntity<BasicResponse> joinTeam(@PathVariable Long teamId, @RequestBody JoinTeamRequestDto teamRequest) {
        Optional<Member> memberOptional = memberService.findOne(teamRequest.getMemberId());
        Member member = memberOptional.orElseThrow(() -> new EntityNotFoundException("회원 조회 실패"));
        teamService.join(member, teamId);

        Optional<Team> teamOptional = teamService.findOne(teamId);
        Team team= teamOptional.orElseThrow(() -> new EntityNotFoundException("팀 조회 실패"));

        TeamListDto teamListDto = new TeamListDto(team);

        BasicResponse basicResponse = new BasicResponse(1, "팀 가입 성공", teamListDto);

        return new ResponseEntity<>(basicResponse, HttpStatus.CREATED);
    }

    @GetMapping("/team/{teamId}") //팀 조회
    public ResponseEntity<BasicResponse> getTeam(@PathVariable Long teamId) {
        Optional<Team> teamOptional = teamService.findOne(teamId);
        Team team= teamOptional.orElseThrow(() -> new EntityNotFoundException("팀 조회 실패"));

        TeamListDto teamListDto = new TeamListDto(team);

        BasicResponse basicResponse = new BasicResponse(1, "팀 조회 성공", teamListDto);

        return new ResponseEntity<>(basicResponse, HttpStatus.CREATED);

    }

    @DeleteMapping("/team/{teamId}") //팀 삭제
    public ResponseEntity<BasicResponse> deleteTeam(@PathVariable Long teamId) {
        Optional<Team> teamOptional = teamService.findOne(teamId);
        Team team = teamOptional.orElseThrow(() -> new EntityNotFoundException("팀 조회 실패"));

        teamService.deleteTeam(team.getId());

        BasicResponse basicResponse = new BasicResponse(1, "팀 삭제 성공", null);

        return new ResponseEntity<>(basicResponse, HttpStatus.OK);
    }
}
