package withSpace_test2.withSpace_test2.responsedto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberBasicResponse {

    private Long id;
    private Integer status;
    private String message;

    public MemberBasicResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
