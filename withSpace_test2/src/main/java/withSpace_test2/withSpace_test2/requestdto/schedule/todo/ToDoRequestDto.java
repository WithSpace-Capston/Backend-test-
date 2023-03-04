package withSpace_test2.withSpace_test2.requestdto.schedule.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoRequestDto {
    private Long categoryId;
    private String description;
    private Boolean completed;
}
