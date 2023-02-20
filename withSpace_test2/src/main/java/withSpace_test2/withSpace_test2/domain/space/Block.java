package withSpace_test2.withSpace_test2.domain.space;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Block {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

    public Block(Page page) {
        this.page = page;
    }
}
