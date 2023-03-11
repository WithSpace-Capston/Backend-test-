//package withSpace_test2.withSpace_test2.service;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//import withSpace_test2.withSpace_test2.domain.Member;
//import withSpace_test2.withSpace_test2.domain.space.MemberSpace;
//import withSpace_test2.withSpace_test2.domain.space.Space;
//import withSpace_test2.withSpace_test2.domain.space.schedule.Category;
//import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;
//import withSpace_test2.withSpace_test2.domain.space.schedule.ToDo;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//class ToDoServiceTest {
//
//    @Autowired
//    ToDoService toDoService;
//
//    @Test
//    public void 할일_생성() {
//        Member member = new Member();
//        Space space = new MemberSpace(member);
//        Schedule schedule = new Schedule(space);
//        Category category = new Category(schedule, "공부");
//
//        ToDo todo = new ToDo(category, "스프링", false, LocalDateTime.now());
//
//        Long saveToDoId = toDoService.makeTodo(todo);
//        Optional<ToDo> findToDo = toDoService.findToDo(todo.getId());
//
//        Assertions.assertThat(findToDo.get().getId()).isEqualTo(saveToDoId);
//    }
//
//}