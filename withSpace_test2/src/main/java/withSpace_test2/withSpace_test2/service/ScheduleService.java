package withSpace_test2.withSpace_test2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;
import withSpace_test2.withSpace_test2.repository.ScheduleRepository;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Long makeSchedule(Schedule schedule) {
        Schedule saveSchedule = scheduleRepository.save(schedule);
        return saveSchedule.getId();
    }

    public Optional<Schedule> findSchedule(Long scheduleId) {
        Optional<Schedule> findSchedule = scheduleRepository.findById(scheduleId);
        return findSchedule;
    }

}
