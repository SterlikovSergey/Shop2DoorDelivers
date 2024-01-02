package st.sergey.minsky.shop2doordelivers.mapper;

import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.CategoryDto;
import st.sergey.minsky.shop2doordelivers.model.Category;

@Component
public class CategoryMapper {
    public Category categoryDtoToCategory(CategoryDto dto) {
        Category category = new Category();
        String name = capitalizeFirstLetter(dto.getName());
        category.setName(name);
        return category;
    }
    public String capitalizeFirstLetter(String word) {
        if (word == null || word.isEmpty()) {
            return "";
        }
        char firstLetter = Character.toUpperCase(word.charAt(0));
        return firstLetter + word.substring(1);
    }
}
