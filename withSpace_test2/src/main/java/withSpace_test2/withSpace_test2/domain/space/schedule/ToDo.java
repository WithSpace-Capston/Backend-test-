package withSpace_test2.withSpace_test2.domain.space.schedule;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDo {

    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    private String content;

    private Boolean completed;

    private LocalDateTime date;

    public ToDo(Category category) {
        this.category = category;
    }

    public ToDo(Category category, String content, Boolean completed, LocalDateTime date) {
        this.category = category;
        this.content = content;
        this.completed = completed;
        this.date = date;
    }
}
