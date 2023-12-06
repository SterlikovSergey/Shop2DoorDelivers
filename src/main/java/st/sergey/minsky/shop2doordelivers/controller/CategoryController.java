package st.sergey.minsky.shop2doordelivers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import st.sergey.minsky.shop2doordelivers.dto.CategoryDto;
import st.sergey.minsky.shop2doordelivers.mapper.CategoryMapper;
import st.sergey.minsky.shop2doordelivers.model.Category;
import st.sergey.minsky.shop2doordelivers.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<Category>> readAll() {
        return new ResponseEntity<>(categoryService.readAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Category> create(@RequestBody CategoryDto dto) {
        return new ResponseEntity<>(categoryService.create(categoryMapper.categoryDtoToCategory(dto)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return HttpStatus.OK;
    }

}
