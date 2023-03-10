package withSpace_test2.withSpace_test2.responsedto.space;

import lombok.Data;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.responsedto.space.page.PageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberSpaceDto {

    private Long spaceId;
    private List<PageDto> pageList = new ArrayList<>();

//    public MemberSpaceDto(Member member) {
//        this.id = member.getMemberSpace().getId();
//        this.pageList = member.getMemberSpace().getPageList().stream()
//                .map(page -> new PageDto(page))
//                .collect(Collectors.toList());
//    }

    public MemberSpaceDto(Member member) {
        this.spaceId = member.getMemberSpace().getId();
        this.pageList = member.getMemberSpace().getPageList().stream()
                .filter(page -> page.getParentPage() == null)
                .map(page -> new PageDto(page))
                .collect(Collectors.toList());
    }
}
