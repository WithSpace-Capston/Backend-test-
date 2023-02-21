package withSpace_test2.withSpace_test2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.Team;
import withSpace_test2.withSpace_test2.domain.space.MemberSpace;
import withSpace_test2.withSpace_test2.domain.space.Space;
import withSpace_test2.withSpace_test2.domain.space.TeamSpace;
import withSpace_test2.withSpace_test2.repository.SpaceRepository;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SpaceService {

    private final SpaceRepository spaceRepository;

    public Space assignSpace(Object obj) { //스페이스 할당
        if (obj instanceof Member) {
            MemberSpace memberSpace = new MemberSpace((Member) obj);
            memberSpace = spaceRepository.save(memberSpace);
            ((Member) obj).setMemberSpace(memberSpace);
            return memberSpace;
        } else if (obj instanceof Team) {
            TeamSpace teamSpace = new TeamSpace((Team) obj);
            teamSpace = spaceRepository.save(teamSpace);
            ((Team) obj).setTeamSpace(teamSpace);
            return teamSpace;
        } else {
            // 여기오면 안됨
            return null;
        }
    }


}
