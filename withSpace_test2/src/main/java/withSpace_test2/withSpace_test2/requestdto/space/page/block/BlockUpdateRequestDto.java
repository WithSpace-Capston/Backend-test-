package withSpace_test2.withSpace_test2.requestdto.space.page.block;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlockUpdateRequestDto {
    private Long memberId; //누가 수정했는지 위해서
    private LocalDateTime updatedAt;
    private String content;
}
