package withSpace_test2.withSpace_test2.responsedto.schedule;

import lombok.Data;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ScheduleDto {
    private Long id;
    private Long spaceId;
    private int categoryCount;

    private List<CategoryDto> categories;

    public ScheduleDto(Schedule schedule) {
        id = schedule.getId();
        spaceId = schedule.getSpace().getId();
        categories = schedule.getCategories().stream()
                .map(category -> new CategoryDto(category))
                .collect(Collectors.toList());
        categoryCount = categories.size();
    }
}
