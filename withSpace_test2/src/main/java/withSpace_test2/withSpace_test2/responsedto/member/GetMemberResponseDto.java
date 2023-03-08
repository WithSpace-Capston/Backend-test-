package withSpace_test2.withSpace_test2.responsedto.member;

import lombok.Data;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.friend.FriendStatus;
import withSpace_test2.withSpace_test2.responsedto.friend.FriendDto;
import withSpace_test2.withSpace_test2.responsedto.memberTeam.MemberTeamResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GetMemberResponseDto {

    private Long id;
    private String email;
    private String password;
    private String memberName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean status;

    private int teamCount;
    private List<MemberTeamResponseDto> memberTeamList = new ArrayList<>();

    private int friendCount;
    private List<FriendDto> friendList = new ArrayList<>();




    public GetMemberResponseDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.memberName = member.getMemberName();
        this.createdAt = member.getCreatedAt();
        this.updatedAt = member.getUpdatedAt();
        this.status = member.getStatus();

        // 회원의 입장에서 팀들의 정보 담기
        if (member.getMemberTeams() != null) {
            memberTeamList = member.getMemberTeams().stream()
                    .map(memberTeam -> new MemberTeamResponseDto(memberTeam))
                    .collect(Collectors.toList());
            teamCount = memberTeamList.size();
        }


        // 친구요청을 건 상대  && status가 accepted인 경우 friendList에 저장
        friendList = member.getFriendRequester().stream()
                .filter(friendShip -> {
                    if(friendShip.getStatus() == FriendStatus.ACCEPTED)
                        return true;
                    else
                        return false;
                })
                .map(friendShip -> new FriendDto(friendShip))
                .collect(Collectors.toList());
        friendCount = friendList.size();
    }

}
