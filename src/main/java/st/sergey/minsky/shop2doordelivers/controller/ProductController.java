package st.sergey.minsky.shop2doordelivers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import st.sergey.minsky.shop2doordelivers.dto.ProductDto;
import st.sergey.minsky.shop2doordelivers.dto.ProductStoreDto;
import st.sergey.minsky.shop2doordelivers.mapper.ProductMapper;
import st.sergey.minsky.shop2doordelivers.mapper.ProductStoreMapper;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.service.CategoryService;
import st.sergey.minsky.shop2doordelivers.service.ProductService;
import st.sergey.minsky.shop2doordelivers.validations.ResponseErrorValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ProductStoreMapper productStoreMapper;
    private final ResponseErrorValidator responseErrorValidator;
    private final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody ProductDto dto,
                                          BindingResult result) {
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(result);
        if(!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        return new ResponseEntity<>(productService.create(productMapper.productDtoToProduct(dto)), HttpStatus.CREATED);
    }



    @PostMapping("/add-amount-store/{productId}")
    public ResponseEntity<Object> addProductToStore(@Valid @RequestBody ProductStoreDto dto,
                                                     @PathVariable Long productId,
                                                     BindingResult result) {
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(result);
        if(!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        Product product = productStoreMapper.productStoreDtoToProduct(dto);
        product.setId(productId);
        return new ResponseEntity<>(productService.setAmountStore(product),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> readAll() {
        return new ResponseEntity<>(productService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> readAllByCategory(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.readByCategoryId(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return HttpStatus.OK;
    }

}
