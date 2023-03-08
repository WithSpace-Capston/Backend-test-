package withSpace_test2.withSpace_test2.responsedto.memberTeam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import withSpace_test2.withSpace_test2.domain.MemberTeam;
import withSpace_test2.withSpace_test2.domain.Team;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberTeamResponseDto {


    private Long teamId;
    private String teamName;




    public MemberTeamResponseDto(MemberTeam memberTeam) {
        Team team = memberTeam.getTeam();
        this.teamId = team.getId();
        this.teamName = team.getTeamName();
    }
}
