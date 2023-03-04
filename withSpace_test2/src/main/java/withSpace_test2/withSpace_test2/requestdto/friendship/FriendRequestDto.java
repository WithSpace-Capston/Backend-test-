package withSpace_test2.withSpace_test2.requestdto.friendship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequestDto {
    private Long id;
    private Long friendId;
}
