package st.sergey.minsky.shop2doordelivers.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.ProductDto;
import st.sergey.minsky.shop2doordelivers.model.Category;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.service.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryService categoryService;

    public Product productDtoToProduct(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setAmount(dto.getAmount());
        Category category = categoryService.readById(dto.getCategoryId());
        product.setCategory(category);
        return product;
    }
}
