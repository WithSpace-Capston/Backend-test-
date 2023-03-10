package withSpace_test2.withSpace_test2.responsedto.space.page.block;

import lombok.AllArgsConstructor;
import lombok.Data;
import withSpace_test2.withSpace_test2.domain.space.Block;
import withSpace_test2.withSpace_test2.requestdto.space.page.block.BlockUpdateRequestDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UpdateBlockResponse {
    private Long blockId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String content;

    public UpdateBlockResponse(Block block) {
        this.updatedAt = block.getUpdatedAt();
        this.content = block.getContent();
    }
}
