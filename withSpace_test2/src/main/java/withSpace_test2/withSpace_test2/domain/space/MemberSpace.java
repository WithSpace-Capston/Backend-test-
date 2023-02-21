package withSpace_test2.withSpace_test2.domain.space;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import withSpace_test2.withSpace_test2.domain.Member;

@Entity
@DiscriminatorValue("member")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSpace extends Space {


    //@Id @GeneratedValue
    //@Column(name = "space_id")
    //private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    public MemberSpace(Member member) {
        this.member = member;
    }
}


