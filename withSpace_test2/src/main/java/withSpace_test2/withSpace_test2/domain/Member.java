package withSpace_test2.withSpace_test2.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import withSpace_test2.withSpace_test2.domain.friend.FriendShip;
import withSpace_test2.withSpace_test2.domain.space.MemberSpace;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {   //회원

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<MemberTeam> memberTeams = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberSpace memberSpace;

    @OneToMany(mappedBy = "member")
    private List<FriendShip> friendRequester = new ArrayList<>(); //친구 신청한 사람

    @OneToMany(mappedBy = "friend")
    private List<FriendShip> friendRequestee = new ArrayList<>();//친구 신청 받은 사람


    private String email;
    private String password;
    private String memberName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;


}