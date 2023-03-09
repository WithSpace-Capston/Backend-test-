package withSpace_test2.withSpace_test2.requestdto.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeamRequestDto {
    private Long memberId;
    private String teamName;
}
