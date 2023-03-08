package withSpace_test2.withSpace_test2.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import withSpace_test2.withSpace_test2.domain.space.Page;
import withSpace_test2.withSpace_test2.responsedto.space.page.PageDetailDto;
import withSpace_test2.withSpace_test2.service.PageService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    //페이지 생성은 SpaceController에서

    @GetMapping("/page/{pageId}") //페이지 조회
    public ResponseEntity<PageDetailDto> getPage(@PathVariable Long pageId) {
        Optional<Page> optionalPage = pageService.findOne(pageId);
        Page page = optionalPage.orElseThrow(() -> new EntityNotFoundException("페이지를 찾을 수 없습니다. pageId: " + pageId));

        PageDetailDto pageDetailDto = new PageDetailDto(page);

        return ResponseEntity.ok(pageDto);
    }

}
