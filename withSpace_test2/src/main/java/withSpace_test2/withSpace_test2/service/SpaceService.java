package withSpace_test2.withSpace_test2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.Team;
import withSpace_test2.withSpace_test2.domain.space.MemberSpace;
import withSpace_test2.withSpace_test2.domain.space.Space;
import withSpace_test2.withSpace_test2.domain.space.TeamSpace;
import withSpace_test2.withSpace_test2.repository.SpaceRepository;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SpaceService {

    private final SpaceRepository spaceRepository;

    public Optional<Space> findOne(Long spaceId) {
        return spaceRepository.findById(spaceId);
    }



    /**
     * 이 메소드를 쓸지말지 모르겠음..
     *
     * 1번방법
     * 회원들은 무조건 Space를 가져야하니 Member가 회원가입-join하면
     * 그냥 거기서 바로 스페이스 생성해다가 연관관계도 연결짓고 save도 해줬는데
     * 그럼 얘는 필요없지않나.. 팀도 마찬가지고..?
     *      ㄴ 이 방법의 문제는 MemberService -> SpaceRepository 이런 모양이 나와버림
     *
     * 2번방법
     * 아래 메소드를 쓸라면 MemberService가 SpaceService를 의존하는 모양이어야 하는데
     * 그건 좀 이상한지..
     *
     *
     * 결론적으로
     * 다른 서비스가 다른 repository를 의존하는게 더 괜찮은지
     * 서비스가 서비스를 의존하는게 더 괜찮은건지 모르겠다
     *
     * 고치는건 금방고칠수있고 1번이 맞는거같아서 일단 1번으로 진행~..
     *
     */
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
