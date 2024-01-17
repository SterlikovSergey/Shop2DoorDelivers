package st.sergey.minsky.shop2doordelivers.mapper;

import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.ProductDto;
import st.sergey.minsky.shop2doordelivers.model.Category;
import st.sergey.minsky.shop2doordelivers.model.Product;

@Component
public class ProductMapper {
    public Product productDtoToProduct(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .category(Category.builder()
                        .id(dto.getCategoryId())
                        .build())
                .build();
    }
}
