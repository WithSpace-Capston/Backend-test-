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

    public Optional<ToDo> findToDo(Long todoId) {
        Optional<ToDo> findTodo = toDoRepository.findById(todoId);
        return findTodo;
    }

    @Transactional
    public Long updateDescription(Long id, String description) {
        Optional<ToDo> findToDo = toDoRepository.findById(id);
        findToDo.get().changeDescription(description);
        return findToDo.get().getId();
    }

    @Transactional
    public Long updateCompleted(Long id, Boolean completed) {
        Optional<ToDo> findToDo = toDoRepository.findById(id);
        findToDo.get().updateCompleted(completed);
        return findToDo.get().getId();
    }

    @Transactional
    public void deleteToDo(Long id) {
        toDoRepository.deleteById(id);
    }
}
