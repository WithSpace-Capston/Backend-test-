package domain.space;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class MemberSpaceId implements Serializable {

    private Long memberId;

    private Long spaceId;
}