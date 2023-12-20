package st.sergey.minsky.shop2doordelivers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import st.sergey.minsky.shop2doordelivers.dto.CategoryDto;
import st.sergey.minsky.shop2doordelivers.mapper.CategoryMapper;
import st.sergey.minsky.shop2doordelivers.repository.view.CategoryView;
import st.sergey.minsky.shop2doordelivers.service.CategoryService;
import st.sergey.minsky.shop2doordelivers.validations.ResponseErrorValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final ResponseErrorValidator responseErrorValidator;

    @GetMapping
    public ResponseEntity<List<CategoryView>> readAll() {
        List<CategoryView> categoryViews = categoryService.readAll();
        return ResponseEntity.ok(categoryViews);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CategoryDto dto, BindingResult result) {
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(result);
        if(!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        return new ResponseEntity<>(categoryService.create(categoryMapper.categoryDtoToCategory(dto)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return HttpStatus.OK;
    }

}
