package withSpace_test2.withSpace_test2.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.Team;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {



}
