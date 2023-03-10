package withSpace_test2.withSpace_test2.domain.space;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import withSpace_test2.withSpace_test2.domain.Member;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Block {

    @Id
    @GeneratedValue
    @Column(name = "block_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by_member_id")
    private Member updatedBy;

    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Block(Page page, Member member) { //블럭생성
        this.page = page;
        page.getBlockList().add(this);

        LocalDateTime now = LocalDateTime.now();

        createdAt = now;
        updatedAt = now;

        createdBy = member;
        updatedBy = member; //일단 생성시에는 만든사람이 곧 최근에 업데이트한 사람으로

    }
}
