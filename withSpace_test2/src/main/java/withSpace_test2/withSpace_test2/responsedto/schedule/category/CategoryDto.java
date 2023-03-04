package withSpace_test2.withSpace_test2.responsedto.schedule.category;

import lombok.Data;
import withSpace_test2.withSpace_test2.domain.space.schedule.Category;
import withSpace_test2.withSpace_test2.responsedto.schedule.ToDoDto;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CategoryDto {
    private Long id;
    private String title;

    private int toDoCount;

    private List<ToDoDto> toDoList;

    public CategoryDto(Category category) {
        id = category.getId();
        title = category.getTitle();
        toDoList = category.getTodoList().stream()
                .map(toDo -> new ToDoDto(toDo))
                .collect(Collectors.toList());
        toDoCount = toDoList.size();
    }
}
