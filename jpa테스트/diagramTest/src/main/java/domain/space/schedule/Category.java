package domain.space.schedule;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Schedule schedule;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ToDo> todoList = new ArrayList<>();

    private String title; //카테고리 제목

    public Category(Schedule schedule) {
        this.schedule = schedule;
        this.title = "test";
    }

}
