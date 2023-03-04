package withSpace_test2.withSpace_test2.responsedto.friend;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendFriendShipResponseDto {
    private Long id;
    private Integer status;
    private String message;
}
