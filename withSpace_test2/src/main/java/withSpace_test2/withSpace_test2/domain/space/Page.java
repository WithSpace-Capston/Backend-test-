package withSpace_test2.withSpace_test2.domain.space;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Page {

    @Id
    @GeneratedValue
    @Column(name = "page_id")
    private Long id;

    @OneToMany(mappedBy = "parentPage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Page> childPages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_page_id")
    private Page parentPage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    private Space space;


    @OneToMany(mappedBy = "page")
    private List<Block> blockList = new ArrayList<>();

    private String title;


    public void addchildPage(Page page) {
        page.setParentPage(this);
        this.childPages.add(page);
    }

}
