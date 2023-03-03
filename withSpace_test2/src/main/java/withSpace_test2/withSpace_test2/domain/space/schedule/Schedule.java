package withSpace_test2.withSpace_test2.domain.space.schedule;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import withSpace_test2.withSpace_test2.domain.space.Space;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    @Id
    @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "space_id")
    private Space space;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

    public Schedule(Space space) {
        this.space = space;
    }

}
