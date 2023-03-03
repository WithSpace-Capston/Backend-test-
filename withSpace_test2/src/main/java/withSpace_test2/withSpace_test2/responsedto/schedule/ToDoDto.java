package withSpace_test2.withSpace_test2.responsedto.schedule;

import lombok.Data;
import withSpace_test2.withSpace_test2.domain.space.schedule.ToDo;

import java.time.LocalDateTime;

@Data
public class ToDoDto {
    private Long id;
    private String description;
    private Boolean completed;
    private LocalDateTime date;

    public ToDoDto(ToDo toDo) {
        id = toDo.getId();
        description = toDo.getDescription();
        completed = toDo.getCompleted();
        date = toDo.getDate();
    }
}
