package st.sergey.minsky.shop2doordelivers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.model.Category;
import st.sergey.minsky.shop2doordelivers.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> readAll(){
        return categoryRepository.findAll();
    }

    public Category readById(Long id){
        return categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Category not found " + id));
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }
}
