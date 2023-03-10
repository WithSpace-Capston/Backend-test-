package withSpace_test2.withSpace_test2.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.space.Block;
import withSpace_test2.withSpace_test2.domain.space.Page;
import withSpace_test2.withSpace_test2.domain.space.Space;
import withSpace_test2.withSpace_test2.repository.BlockRepository;
import withSpace_test2.withSpace_test2.repository.MemberRepository;
import withSpace_test2.withSpace_test2.repository.PageRepository;
import withSpace_test2.withSpace_test2.repository.SpaceRepository;
import withSpace_test2.withSpace_test2.requestdto.space.page.PageCreateRequestDto;
import withSpace_test2.withSpace_test2.requestdto.space.page.PageUpdateRequestDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PageService {

    private final PageRepository pageRepository;
    private final MemberRepository memberRepository;
    private final BlockRepository blockRepository;
    private final SpaceRepository spaceRepository;


    @Transactional
    public Long makePage(Long spaceId, PageCreateRequestDto pageCreateRequestDto) {
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new EntityNotFoundException("스페이스 조회 실패 >> at PageService.makePage() "));
        Page page = new Page(pageCreateRequestDto.getTitle());

        setPageSpace(page, space);

        // Find parent page, if specified
        if (pageCreateRequestDto.getParentPageId() != null && pageCreateRequestDto.getParentPageId().isPresent()) {
            Long parentPageId = pageCreateRequestDto.getParentPageId().orElse(null);
            Page parentPage = pageRepository.findById(parentPageId)
                    .orElseThrow(() -> new EntityNotFoundException("페이지 찾기 실패. parentPageId: " + parentPageId));
            parentPage.addchildPage(page);
            page.setParentPage(parentPage);
        }

        pageRepository.save(page);

        return page.getId();
    }


    @Transactional
    public void setPageSpace(Page page, Space space) {
        page.setSpace(space);
        space.getPageList().add(page);
    }

    public Optional<Page>findOne(Long pageId) {
        return pageRepository.findById(pageId);
    }

    @Transactional
    public void updatePage(Long pageId, PageUpdateRequestDto requestDto) {
        Optional<Page> optionalPage = pageRepository.findById(pageId);
        Page page = optionalPage.orElseThrow(() -> new EntityNotFoundException("페이지를 찾을 수 없습니다. pageId: " + pageId));

        if (requestDto.getTitle() != null) {
            page.setTitle(requestDto.getTitle());
        }

        page.setUpdatedAt(LocalDateTime.now());
    }

    @Transactional
    public void deletePage(Long pageId) {
        Optional<Page> optionalPage = pageRepository.findById(pageId);
        Page page = optionalPage.orElseThrow(()
                -> new EntityNotFoundException("페이지를 찾을 수 없습니다. pageId: " + pageId));

        pageRepository.delete(page);
    }


}
