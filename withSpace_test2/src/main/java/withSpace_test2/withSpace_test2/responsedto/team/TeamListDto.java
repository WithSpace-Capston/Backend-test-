package withSpace_test2.withSpace_test2.responsedto.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import withSpace_test2.withSpace_test2.domain.MemberTeam;
import withSpace_test2.withSpace_test2.domain.Team;
import withSpace_test2.withSpace_test2.responsedto.memberTeam.MemberTeamResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class TeamListDto {
    private Long teamId;
    private int memberCount;
    private String teamName;
    private List<MemberTeamResponseDto> memberTeamResponseDtoList;

    public TeamListDto(MemberTeam memberTeam) {
        Team team = memberTeam.getTeam();
        this.teamId = team.getId();
        this.teamName = memberTeam.getTeamName();
        this.memberCount = team.getMemberCount();

        memberTeamResponseDtoList = memberTeam.getTeam().getMemberTeams().stream()
                .map(MemberTeamResponseDto::new)
                .collect(Collectors.toList());

    }
}
