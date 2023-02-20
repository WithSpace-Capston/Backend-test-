package withSpace_test2.withSpace_test2.domain.space;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import withSpace_test2.withSpace_test2.domain.Team;

@Entity
@DiscriminatorValue("team")
@Getter
@Setter
public class TeamSpace extends Space {

    //@Id @GeneratedValue
    //private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public TeamSpace(Team team) {
        this.team = team;
    }
}