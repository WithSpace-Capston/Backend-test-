package withSpace_test2.withSpace_test2.requestdto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinRequestDto {

    private String memberName;
    private String email;
    private String password;

}
