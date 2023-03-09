package withSpace_test2.withSpace_test2.responsedto.space.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import withSpace_test2.withSpace_test2.domain.space.Page;
import withSpace_test2.withSpace_test2.responsedto.space.page.block.BlockDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDetailDto {
    private Long pageId;
    private String pageTitle;
    private Long spaceId;
    private LocalDateTime createdTime;
    private LocalDateTime lastEditedTime;
    private Optional<Long> parentPageId;
    private List<BlockDto> blockList;

    public PageDetailDto(Page page) {
        this.pageId = page.getId();
        this.pageTitle = page.getTitle();
        this.spaceId = page.getSpace().getId();
        this.createdTime = page.getCreatedAt();
        this.lastEditedTime = page.getUpdatedAt();
        if (page.getParentPage() != null) {
            this.parentPageId = page.getParentPage().getId().describeConstable();
        }
        this.blockList = page.getBlockList().stream().map(BlockDto::new).collect(Collectors.toList());

    }

}
