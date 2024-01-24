package st.sergey.minsky.shop2doordelivers.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exception.CategoryExistException;
import st.sergey.minsky.shop2doordelivers.exception.CategoryNotFoundException;
import st.sergey.minsky.shop2doordelivers.exception.StoreExistException;
import st.sergey.minsky.shop2doordelivers.model.Category;
import st.sergey.minsky.shop2doordelivers.repository.CategoryRepository;
import st.sergey.minsky.shop2doordelivers.repository.view.CategoryView;
import st.sergey.minsky.shop2doordelivers.utils.StringUtil;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryService {
    public static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;
    private final StringUtil stringUtil;

    public Object create(Category category) {
        String capitalizedCategoryName = stringUtil.capitalizeFirstLetter(category.getName());
        if(isCategoryNameUnique(capitalizedCategoryName)){
            return saveCategory(category, capitalizedCategoryName);
        }else {
            LOG.error("The category {} already exist. Please check credentials", capitalizedCategoryName);
            throw new StoreExistException("The category "
                    + category.getName() + " already exist. Please check credentials");
        }
    }

    public Set<CategoryView> readAll() {
        return categoryRepository.findAllByOrderByNameAsc();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Category not found " + id));
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public Object saveCategory(Category category, String capitalizedCategoryName){
        try {
            LOG.info("Saving category {}", capitalizedCategoryName);
            return categoryRepository.save(Category
                    .builder()
                    .name(capitalizedCategoryName)
                    .build());
        } catch (DataIntegrityViolationException e) {
            LOG.error("Error during category. {}", e.getMessage());
            throw new CategoryExistException("The category " + capitalizedCategoryName +
                    " already exist. Please check credentials");
        }
    }

    public boolean isCategoryNameUnique(String name){
        return !categoryRepository.existsByName(name);
    }
}


