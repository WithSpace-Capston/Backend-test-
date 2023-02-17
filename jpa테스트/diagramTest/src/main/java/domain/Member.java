package domain;

import domain.space.MemberSpace;
import domain.space.Space;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "member")
    private List<MemberSpace> memberSpaces = new ArrayList<>();


    public void setMemberSpace(MemberSpace memberSpace) {
        memberSpaces.add(memberSpace);
    }

    private String email;
    private String password;
    private String memberName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;



}