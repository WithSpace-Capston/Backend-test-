package withSpace_test2.withSpace_test2.responsedto.space.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import withSpace_test2.withSpace_test2.domain.space.Page;
import withSpace_test2.withSpace_test2.responsedto.space.page.block.BlockDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDetailDto {
    private Long pageId;
    private String title;

    private Long parentId;
    private List<PageDto> childPageList;
    private List<BlockDto> blockDtoList;

    public PageDetailDto(Page page) {
    }
}
