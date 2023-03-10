package withSpace_test2.withSpace_test2.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import withSpace_test2.withSpace_test2.domain.space.TeamSpace;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    @OneToMany(mappedBy = "team")
    private List<MemberTeam> memberTeams = new ArrayList<>();

    @OneToOne(mappedBy = "team", cascade = CascadeType.ALL)
    private TeamSpace teamSpace;

    private int memberCount;

    private String teamName;

    //연관관계 편의 메소드//
    public void setTeamSpace(TeamSpace teamSpace) {
        this.teamSpace = teamSpace;
        teamSpace.setTeam(this);
    }

    public Team(String teamName) {
        this.teamName = teamName;
        memberCount = 0;
    }

}
