package domain.space;

import domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("member")
@Getter
@Setter
public class MemberSpace extends Space {

    @Id @GeneratedValue
    @Column(name = "space_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public MemberSpace(Member member) {
        this.member = member;
    }
}