package withSpace_test2.withSpace_test2.domain.space;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "space_type") //member스페이스, team 스페이스
@Getter
@Setter
public abstract class Space {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "space_type", insertable = false, updatable = false)
    private String type;

    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL)
    private List<Page> pageList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "space")
    private Schedule schedule;



    public Space(){
        this.schedule = new Schedule(this); //스페이스 생성시 바로 만들어서 넣어줌
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
