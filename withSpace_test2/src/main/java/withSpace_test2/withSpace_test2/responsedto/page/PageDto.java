package withSpace_test2.withSpace_test2.responsedto.page;

import lombok.Data;
import withSpace_test2.withSpace_test2.domain.space.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PageDto {
    private Long id;
    private String title;
    private List<PageDto> childPages = new ArrayList<>();

    public PageDto(Page page) {
        this.id = page.getId();
        this.title = page.getTitle();
        this.childPages = page.getChildPages().stream().map(PageDto::new).collect(Collectors.toList());
    }
}
