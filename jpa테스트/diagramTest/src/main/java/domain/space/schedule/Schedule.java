package domain.space.schedule;

import domain.space.Space;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Schedule {

    @Id @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "space_id")
    private Space space;

    public Schedule(Space space) {
        this.space = space;
    }

}
