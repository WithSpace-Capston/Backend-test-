package withSpace_test2.withSpace_test2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import withSpace_test2.withSpace_test2.domain.MemberTeam;
import withSpace_test2.withSpace_test2.domain.MemberTeamId;

@Repository
public interface MemberTeamRepository extends JpaRepository<MemberTeam, MemberTeamId> {
    void deleteByTeamId(Long teamId);
}