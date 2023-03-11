package withSpace_test2.withSpace_test2.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import withSpace_test2.withSpace_test2.domain.MemberTeam;
import withSpace_test2.withSpace_test2.domain.MemberTeamId;

import java.util.List;

@Repository
public interface MemberTeamRepository extends JpaRepository<MemberTeam, MemberTeamId>, QuerydslPredicateExecutor<MemberTeam> {
    @Query("SELECT mt FROM MemberTeam mt WHERE mt.team.id = :teamId")
    List<MemberTeam> findByTeamId(@Param("teamId") Long teamId);

    List<MemberTeam> findAll(Predicate predicate);
}