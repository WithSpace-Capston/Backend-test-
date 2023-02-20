package withSpace_test2.withSpace_test2.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import withSpace_test2.withSpace_test2.domain.Team;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TeamRepository {

    private final EntityManager em;

    public Team save(Team team) {
        em.persist(team);
        return team;
    }

    public Optional<Team> findById(Long id) {
        Team team = em.find(Team.class, id);
        return Optional.ofNullable(team);
    }


}
