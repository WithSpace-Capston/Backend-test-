package withSpace_test2.withSpace_test2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.space.MemberSpace;
import withSpace_test2.withSpace_test2.domain.space.Space;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(false)
class ScheduleServiceTest {

    @Autowired
    ScheduleService scheduleService;

    @Test
    public void 스케줄_생성() {
        Member member = new Member();
        Space space = new MemberSpace(member);
        Schedule schedule = new Schedule(space);

        Long scheduleId = scheduleService.makeSchedule(schedule);

        Optional<Schedule> findSchedule = scheduleService.findSchedule(schedule.getId());

        Assertions.assertThat(findSchedule.get().getId()).isEqualTo(scheduleId);
    }
}