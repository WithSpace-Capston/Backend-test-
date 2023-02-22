package withSpace_test2.withSpace_test2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
