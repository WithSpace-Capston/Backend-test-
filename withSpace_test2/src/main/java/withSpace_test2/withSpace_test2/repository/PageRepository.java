package withSpace_test2.withSpace_test2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import withSpace_test2.withSpace_test2.domain.space.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
}
