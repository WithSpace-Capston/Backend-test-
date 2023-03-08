package withSpace_test2.withSpace_test2.responsedto.memberTeam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.MemberTeam;
import withSpace_test2.withSpace_test2.domain.Team;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberTeamResponseDto {


    private Long userId;
    private String memberName;
    private boolean status;




    public MemberTeamResponseDto(MemberTeam memberTeam) {
        Member member = memberTeam.getMember();
        this.userId = member.getId();
        this.memberName = member.getMemberName();

        //this.status = member.getStatus();
        this.status = member.getStatus() != null && member.getStatus().booleanValue();
    }
}
