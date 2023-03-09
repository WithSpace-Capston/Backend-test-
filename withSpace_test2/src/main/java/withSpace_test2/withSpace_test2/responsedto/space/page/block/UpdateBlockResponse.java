package withSpace_test2.withSpace_test2.responsedto.space.page.block;

import lombok.AllArgsConstructor;
import lombok.Data;
import withSpace_test2.withSpace_test2.requestdto.space.page.block.BlockUpdateRequestDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UpdateBlockResponse {
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String content;

    public UpdateBlockResponse(String memberName, BlockUpdateRequestDto requestDto) {
        this.updatedBy = memberName;
        this.updatedAt = requestDto.getUpdatedAt();
        this.content = requestDto.getContent();
    }
}
