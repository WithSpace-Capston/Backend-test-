package withSpace_test2.withSpace_test2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import withSpace_test2.withSpace_test2.domain.space.schedule.Category;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;
import withSpace_test2.withSpace_test2.domain.space.schedule.ToDo;
import withSpace_test2.withSpace_test2.requestdto.schedule.category.CategoryRequestDto;
import withSpace_test2.withSpace_test2.requestdto.schedule.todo.ToDoRequestDto;
import withSpace_test2.withSpace_test2.responsedto.schedule.category.CategoryCreateResponseDto;
import withSpace_test2.withSpace_test2.responsedto.BasicResponse;
import withSpace_test2.withSpace_test2.responsedto.schedule.ScheduleDto;
import withSpace_test2.withSpace_test2.responsedto.schedule.todo.ToDoCreateResponseDto;
import withSpace_test2.withSpace_test2.service.CategoryService;
import withSpace_test2.withSpace_test2.service.ScheduleService;
import withSpace_test2.withSpace_test2.service.ToDoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private static final int CREATED = 201;

    private final ScheduleService scheduleService;

    private final CategoryService categoryService;

    private final ToDoService toDoService;

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
        Optional<Schedule> schedule = scheduleService.findSchedule(categoryRequestDto.getScheduleId());
        Category category = new Category(schedule.get(), categoryRequestDto.getTitle());

        Long saveCategoryId = categoryService.makeCategory(category);
        CategoryCreateResponseDto categoryCreateResponseDto = new CategoryCreateResponseDto(saveCategoryId, CREATED, "카테고리가 등록되었습니다.");
        return new ResponseEntity<>(categoryCreateResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/todo")
    public ResponseEntity<ToDoCreateResponseDto> createToDo(@RequestBody ToDoRequestDto toDoRequestDto) {
        Optional<Category> findCategory = categoryService.findCategory(toDoRequestDto.getCategoryId());
        ToDo todo = new ToDo(findCategory.get(), toDoRequestDto.getDescription(), toDoRequestDto.getCompleted(), LocalDateTime.now());

        Long saveToDoId = toDoService.makeTodo(todo);
        ToDoCreateResponseDto createResponseDto = new ToDoCreateResponseDto(saveToDoId, CREATED, "할일이 등록되었습니다.");
        return new ResponseEntity<>(createResponseDto, HttpStatus.CREATED);
    }
}
