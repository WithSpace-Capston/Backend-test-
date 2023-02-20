package withSpace_test2.withSpace_test2.domain;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class MemberTeamId implements Serializable { //외래키만으로 복합키를 사용하기 위한 클래스

    private Long memberId;
    private Long teamId;

    public MemberTeamId(Long memberId, Long teamId) {
        this.memberId = memberId;
        this.teamId = teamId;
    }
}
