package withSpace_test2.withSpace_test2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;
import withSpace_test2.withSpace_test2.domain.space.schedule.ToDo;
import withSpace_test2.withSpace_test2.repository.ToDoRepository;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    @Transactional
    public Long makeTodo(ToDo todo) {
        ToDo saveToDo = toDoRepository.save(todo);
        return saveToDo.getId();
    }

    Optional<ToDo> findToDo(Long todoId) {
        Optional<ToDo> findTodo = toDoRepository.findById(todoId);
        return findTodo;
    }
}
