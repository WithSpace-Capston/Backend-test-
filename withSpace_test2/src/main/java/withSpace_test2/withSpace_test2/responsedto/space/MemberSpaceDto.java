package withSpace_test2.withSpace_test2.responsedto.space;

import lombok.Data;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.space.Page;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;
import withSpace_test2.withSpace_test2.responsedto.page.PageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberSpaceDto {

    private Long id;
    private List<PageDto> pageList = new ArrayList<>();

    public MemberSpaceDto(Member member) {
        this.id = member.getMemberSpace().getId();
        this.pageList = member.getMemberSpace().getPageList().stream()
                .map(page -> new PageDto(page))
                .collect(Collectors.toList());
    }
}
