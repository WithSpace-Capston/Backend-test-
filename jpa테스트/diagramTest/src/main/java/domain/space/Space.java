package domain.space;

import domain.Member;
import domain.Team;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "space_type") //member스페이스, team 스페이스
@Getter @Setter
public abstract class Space {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "space_type", insertable = false, updatable = false)
    private String type;

    @OneToMany(mappedBy = "space")
    private List<Page> pageList = new ArrayList<>();

    //스케쥴 넣을 예정



    //양방향 연관관계..
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "team_id")
//    private Team team;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;



}
