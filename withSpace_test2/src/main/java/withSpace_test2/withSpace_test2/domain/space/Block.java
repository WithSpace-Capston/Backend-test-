package withSpace_test2.withSpace_test2.domain.space;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    private String content;

    public Block(Page page) {
        this.page = page;
    }
}
