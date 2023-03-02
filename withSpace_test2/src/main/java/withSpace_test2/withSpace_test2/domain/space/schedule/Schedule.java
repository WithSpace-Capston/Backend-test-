package withSpace_test2.withSpace_test2.domain.space.schedule;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import withSpace_test2.withSpace_test2.domain.space.Space;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    @Id @GeneratedValue
    @Column(name="schedule_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "space_id")
    private Space space;

    public Schedule(Space space) {
        this.space = space;
    }

}
