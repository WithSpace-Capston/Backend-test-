package withSpace_test2.withSpace_test2.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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
    private List<MemberTeam> memberTeams= new ArrayList<>();

    @OneToOne(mappedBy = "member")
    private MemberSpace memberSpace;


    private String email;
    private String password;
    private String memberName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;


}