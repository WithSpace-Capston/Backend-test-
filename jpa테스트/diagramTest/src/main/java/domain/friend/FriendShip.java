package domain.friend;

import domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FriendShip {

    public enum Status {
        PENDING, ACCEPTED, REJECTED
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private Member friend;

    @Enumerated(EnumType.STRING)
    private Status status;

}
