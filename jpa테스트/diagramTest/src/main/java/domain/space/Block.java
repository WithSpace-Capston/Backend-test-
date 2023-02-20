package domain.space;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
