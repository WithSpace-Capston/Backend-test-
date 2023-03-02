package withSpace_test2.withSpace_test2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import withSpace_test2.withSpace_test2.domain.space.schedule.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
