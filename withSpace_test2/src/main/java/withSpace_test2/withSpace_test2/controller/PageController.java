package withSpace_test2.withSpace_test2.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.space.Block;
import withSpace_test2.withSpace_test2.domain.space.Page;
import withSpace_test2.withSpace_test2.requestdto.space.page.block.BlockCreateRequestDto;
import withSpace_test2.withSpace_test2.requestdto.space.page.block.BlockUpdateRequestDto;
import withSpace_test2.withSpace_test2.responsedto.BasicResponse;
import withSpace_test2.withSpace_test2.responsedto.space.page.PageDetailDto;
import withSpace_test2.withSpace_test2.responsedto.space.page.block.BlockDto;
import withSpace_test2.withSpace_test2.responsedto.space.page.block.UpdateBlockResponse;
import withSpace_test2.withSpace_test2.service.BlockService;
import withSpace_test2.withSpace_test2.service.MemberService;
import withSpace_test2.withSpace_test2.service.PageService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;
    private final BlockService blockService;
    private final MemberService memberService;

    //페이지 생성은 SpaceController에서


    @PostMapping("/page/{pageId}/block") //블록 생성
    public ResponseEntity<BasicResponse> createBlock(@PathVariable Long pageId, @RequestBody BlockCreateRequestDto blockCreateRequestDto) {
        Long memberId = blockCreateRequestDto.getMemberId();
        Long blockId = blockService.makeBlock(pageId, memberId);
        Block block = blockService.findOne(blockId)
                .orElseThrow(() -> new EntityNotFoundException("블럭이 없습니다. blockId: " + blockId));

        BasicResponse basicResponse = new BasicResponse(1, "블럭 생성 성공", new BlockDto(block));

        return new ResponseEntity<>(basicResponse, HttpStatus.OK);
    }

    @GetMapping("/page/{pageId}") //페이지 조회
    public ResponseEntity<PageDetailDto> getPage(@PathVariable Long pageId) {
        Optional<Page> optionalPage = pageService.findOne(pageId);
        Page page = optionalPage.orElseThrow(() -> new EntityNotFoundException("페이지를 찾을 수 없습니다. pageId: " + pageId));

        PageDetailDto pageDetailDto = new PageDetailDto(page);

        return ResponseEntity.ok(pageDetailDto);
    }
    @PatchMapping("/block/{blockId}") //블럭 업데이트
    public ResponseEntity<UpdateBlockResponse> updateBlock(@PathVariable Long blockId, @RequestBody BlockUpdateRequestDto requestDto) {
        Optional<Block> optionalBlock = blockService.findOne(blockId);
        Block block = optionalBlock.orElseThrow(() -> new EntityNotFoundException("블럭을 찾을 수 없습니다. blockId: " + blockId));

        Optional<Member> optionalMember = memberService.findOne(requestDto.getMemberId());
        Member member = optionalMember.orElseThrow(() -> new EntityNotFoundException("멤버를 찾을 수 없습니다. in 블럭 업데이트"));

        // 업데이트
        blockService.updateBlock(blockId, requestDto);

        UpdateBlockResponse updateBlockResponse = new UpdateBlockResponse(member.getMemberName(), requestDto);

        return new ResponseEntity<>(updateBlockResponse, HttpStatus.OK);
    }

    //페이지 제목 업데이트필요

}
