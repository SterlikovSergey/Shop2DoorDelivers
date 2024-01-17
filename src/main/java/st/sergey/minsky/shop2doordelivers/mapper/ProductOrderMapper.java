package st.sergey.minsky.shop2doordelivers.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.ProductOrderDto;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.service.ProductService;

@Component
@RequiredArgsConstructor
public class ProductOrderMapper {
    public Product productOrderDtoToProduct(ProductOrderDto dto){
        Product product = new Product();
        product.setId(dto.getId());
        return product;
    }

}
