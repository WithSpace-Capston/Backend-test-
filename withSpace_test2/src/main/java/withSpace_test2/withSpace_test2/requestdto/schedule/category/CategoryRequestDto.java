package withSpace_test2.withSpace_test2.requestdto.schedule.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDto {
    private Long scheduleId;
    private String title;
}
