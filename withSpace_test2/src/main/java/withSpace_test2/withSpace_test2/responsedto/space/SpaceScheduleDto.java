package withSpace_test2.withSpace_test2.responsedto.space;

import lombok.Data;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;
import withSpace_test2.withSpace_test2.responsedto.schedule.category.CategoryDto;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SpaceScheduleDto {
    private Long id;
    private int categoryCount;

    private List<CategoryDto> categories;

    public SpaceScheduleDto(Schedule schedule) {
        id = schedule.getId();
        categories = schedule.getCategories().stream()
                .map(category -> new CategoryDto(category))
                .collect(Collectors.toList());
        categoryCount = categories.size();
    }
}
