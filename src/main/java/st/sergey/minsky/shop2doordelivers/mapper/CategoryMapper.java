package st.sergey.minsky.shop2doordelivers.mapper;

import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.CategoryDto;
import st.sergey.minsky.shop2doordelivers.model.Category;

@Component
public class CategoryMapper {
    public Category categoryDtoToCategory(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}
