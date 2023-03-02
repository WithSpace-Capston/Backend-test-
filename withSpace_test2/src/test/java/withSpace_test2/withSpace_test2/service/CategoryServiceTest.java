package withSpace_test2.withSpace_test2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.space.MemberSpace;
import withSpace_test2.withSpace_test2.domain.space.Space;
import withSpace_test2.withSpace_test2.domain.space.schedule.Category;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Test
    public void 카테고리_생성() {
        Member member = new Member();
        Space space = new MemberSpace(member);
        Schedule schedule = new Schedule(space);
        Category category = new Category(schedule, "공부");
        Long saveCategoryId = categoryService.makeCategory(category);

        Optional<Category> findCategory = categoryService.findCategory(category.getId());

        Assertions.assertThat(findCategory.get().getId()).isEqualTo(saveCategoryId);
    }

}