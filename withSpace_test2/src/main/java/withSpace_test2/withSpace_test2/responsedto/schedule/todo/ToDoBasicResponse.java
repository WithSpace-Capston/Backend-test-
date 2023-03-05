package withSpace_test2.withSpace_test2.responsedto.schedule.todo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ToDoBasicResponse {
    private Long id;
    private Integer status;
    private String message;
}
