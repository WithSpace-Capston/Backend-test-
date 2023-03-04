package withSpace_test2.withSpace_test2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.space.schedule.Category;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;
import withSpace_test2.withSpace_test2.repository.CategoryRepository;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long makeCategory(Category category) {
        Category save = categoryRepository.save(category);
        return save.getId();
    }

    public Optional<Category> findCategory(Long categoryId) {
        Optional<Category> findCategory = categoryRepository.findById(categoryId);
        return findCategory;
    }
}
