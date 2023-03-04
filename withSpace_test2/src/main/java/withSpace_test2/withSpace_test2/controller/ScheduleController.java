package withSpace_test2.withSpace_test2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import withSpace_test2.withSpace_test2.domain.space.schedule.Category;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;
import withSpace_test2.withSpace_test2.requestdto.schedule.category.CategoryRequestDto;
import withSpace_test2.withSpace_test2.responsedto.schedule.category.CategoryCreateResponseDto;
import withSpace_test2.withSpace_test2.responsedto.BasicResponse;
import withSpace_test2.withSpace_test2.responsedto.schedule.ScheduleDto;
import withSpace_test2.withSpace_test2.service.CategoryService;
import withSpace_test2.withSpace_test2.service.ScheduleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private static final int SUCCESS = 201;

    private final ScheduleService scheduleService;

    private final CategoryService categoryService;

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<BasicResponse> schedule(@PathVariable("scheduleId") Long scheduleId) {
        Optional<Schedule> schedule = scheduleService.findSchedule(scheduleId);
        List<ScheduleDto> collect = schedule.stream().map(s -> new ScheduleDto(schedule.get()))
                .collect(Collectors.toList());
        BasicResponse basicResponse = new BasicResponse<>(collect.size(), "스케줄 요청 성공", collect);

        return new ResponseEntity<>(basicResponse, HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryCreateResponseDto> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        System.out.println("categoryRequestDto.getScheduleId() = " + categoryRequestDto.getScheduleId());
        Optional<Schedule> schedule = scheduleService.findSchedule(categoryRequestDto.getScheduleId());
        Category category = new Category(schedule.get(), categoryRequestDto.getTitle());

        Long saveCategoryId = categoryService.makeCategory(category);
        CategoryCreateResponseDto categoryCreateResponseDto = new CategoryCreateResponseDto(saveCategoryId, SUCCESS, "카테고리가 등록되었습니다.");
        return new ResponseEntity<>(categoryCreateResponseDto, HttpStatus.OK);
    }
}
