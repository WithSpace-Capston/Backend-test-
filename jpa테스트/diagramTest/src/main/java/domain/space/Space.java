package domain.space;

import domain.Member;
import domain.Team;
import domain.space.schedule.Category;
import domain.space.schedule.Schedule;
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

    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL)
    private List<Page> pageList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "space"
                , cascade = CascadeType.ALL)
    private Schedule schedule; //스페이스 생성시 바로 만들어서 넣어주기



    public Space(){
        this.schedule = new Schedule(this);
    }



    //양방향 연관관계..
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "team_id")
//    private Team team;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;



}
