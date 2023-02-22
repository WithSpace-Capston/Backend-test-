package withSpace_test2.withSpace_test2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import withSpace_test2.withSpace_test2.domain.space.schedule.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
