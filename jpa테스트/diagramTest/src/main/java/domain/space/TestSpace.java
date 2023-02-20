package domain.space;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class TestSpace {

    @Id
    @GeneratedValue
    @Column(name = "test_id")
    private Long id;

    @Column(name = "space_type", insertable = false, updatable = false)
    private String type;

    @OneToMany(mappedBy = "space")
    private List<Page> pageList = new ArrayList<>();



}
