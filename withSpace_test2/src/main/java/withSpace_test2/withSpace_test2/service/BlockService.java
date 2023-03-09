package withSpace_test2.withSpace_test2.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.space.Block;
import withSpace_test2.withSpace_test2.domain.space.Page;
import withSpace_test2.withSpace_test2.repository.BlockRepository;
import withSpace_test2.withSpace_test2.repository.MemberRepository;
import withSpace_test2.withSpace_test2.repository.PageRepository;
import withSpace_test2.withSpace_test2.requestdto.space.page.block.BlockUpdateRequestDto;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BlockService {

    private final PageRepository pageRepository;
    private final BlockRepository blockRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long makeBlock(Long pageId, Long memberId) { //블럭 생성
        Page page = pageRepository.findById(pageId)
                .orElseThrow(() -> new EntityNotFoundException("페이지를 찾을 수 없습니다. pageId: " + pageId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("멤버를 찾을 수 없습니다. memberId: " + memberId));

        Block block = new Block(page, member);


        blockRepository.save(block);

        return block.getId();
    }

    @Transactional
    public void updateBlock(Long blockId, BlockUpdateRequestDto requestDto) { //블럭 업데이트
        Block block = blockRepository.findById(blockId)
                .orElseThrow(() -> new EntityNotFoundException("블록을 찾을 수 없습니다. blockId: " + blockId));

        if (requestDto.getMemberId() != null) {
            Member updatedBy = memberRepository.findById(requestDto.getMemberId())
                    .orElseThrow(() -> new EntityNotFoundException("멤버를 찾을 수 없습니다. memberId: " + requestDto.getMemberId()));
            block.setUpdatedBy(updatedBy);
        }

        if (requestDto.getUpdatedAt() != null) {
            block.setUpdatedAt(requestDto.getUpdatedAt());
        }

        if (requestDto.getContent() != null) {
            block.setContent(requestDto.getContent());
        }

        blockRepository.save(block);
    }

    public Optional<Block> findOne(Long blockId) {
        return blockRepository.findById(blockId);
    }

}
