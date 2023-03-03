package withSpace_test2.withSpace_test2;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.space.MemberSpace;
import withSpace_test2.withSpace_test2.domain.space.Space;
import withSpace_test2.withSpace_test2.domain.space.schedule.Category;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;
import withSpace_test2.withSpace_test2.domain.space.schedule.ToDo;
import withSpace_test2.withSpace_test2.service.CategoryService;
import withSpace_test2.withSpace_test2.service.ScheduleService;
import withSpace_test2.withSpace_test2.service.ToDoService;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestDB {

    private final CategoryService categoryService;

    private final ScheduleService scheduleService;

    private final ToDoService toDoService;


    @PostConstruct
    public void postConstruct() {
        scheduleInit();
    }

    @Transactional
    public void scheduleInit() {
        Member member = new Member();
        member.setMemberName("memberA");
        Space memberASpace = new MemberSpace(member);
        Schedule schedule = new Schedule(memberASpace);

        scheduleService.makeSchedule(schedule);

        Category studyCategory = new Category(schedule, "공부");
        Category workOutCategory = new Category(schedule, "운동");

        categoryService.makeCategory(studyCategory);
        categoryService.makeCategory(workOutCategory);

        ToDo springToDo = new ToDo(studyCategory, "스프링 공부", false, LocalDateTime.now());
        ToDo codingToDo = new ToDo(studyCategory, "코딩 공부", false, LocalDateTime.now());

        toDoService.makeTodo(springToDo);
        toDoService.makeTodo(codingToDo);

        ToDo healthToDo = new ToDo(workOutCategory, "헬스", false, LocalDateTime.now());
        toDoService.makeTodo(healthToDo);
    }
}
