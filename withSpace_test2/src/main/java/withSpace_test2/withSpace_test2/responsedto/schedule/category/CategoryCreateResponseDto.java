package withSpace_test2.withSpace_test2.responsedto.schedule.category;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryCreateResponseDto {
    private Long id;
    private Integer status;
    private String message;
}
