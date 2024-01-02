package st.sergey.minsky.shop2doordelivers.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exception.CategoryExistException;
import st.sergey.minsky.shop2doordelivers.exception.CategoryNotFoundException;
import st.sergey.minsky.shop2doordelivers.exception.UserExistException;
import st.sergey.minsky.shop2doordelivers.model.Category;
import st.sergey.minsky.shop2doordelivers.repository.CategoryRepository;
import st.sergey.minsky.shop2doordelivers.repository.view.CategoryView;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
@RequiredArgsConstructor
public class CategoryService {

    public static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    public Category create(Category category) {
        try {
            if (isCategoryNameUnique(category.getName())) {
                LOG.info("Saving category {}", category.getName());
                return categoryRepository.save(category);
            } else {
                LOG.error("Error during category. Category with name {} already exists.", category.getName());
                throw new CategoryExistException("The category with name " + category.getName() +
                        " already exists. Please check credentials");
            }
        } catch (DataIntegrityViolationException e) {
            LOG.error("Error during category. {}", e.getMessage());
            throw new CategoryExistException("The category " + category.getName() +
                    " already exist. Please check credentials");
        }
    }


    public Set<CategoryView> readAll(){
        return categoryRepository.findAllByOrderByNameAsc();
    }

    public Category readById(Long id){
        return categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Category not found " + id));
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }



    private boolean isCategoryNameUnique(String categoryName) {
        return !categoryRepository.existsByName(categoryName);
    }
}
