package withSpace_test2.withSpace_test2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;
import withSpace_test2.withSpace_test2.responsedto.BasicResponse;
import withSpace_test2.withSpace_test2.responsedto.schedule.ScheduleDto;
import withSpace_test2.withSpace_test2.service.ScheduleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<BasicResponse> schedule(@PathVariable("scheduleId") Long scheduleId) {
        Optional<Schedule> schedule = scheduleService.findSchedule(scheduleId);
        List<ScheduleDto> collect = schedule.stream().map(s -> new ScheduleDto(schedule.get()))
                .collect(Collectors.toList());
        BasicResponse basicResponse = new BasicResponse<>(collect.size(), "스케줄 요청 성공", collect);

        return new ResponseEntity<>(basicResponse, HttpStatus.OK);
    }
}
