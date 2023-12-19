package st.sergey.minsky.shop2doordelivers.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.OrderDto;
import st.sergey.minsky.shop2doordelivers.model.Order;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ProductService productService;
    public Order orderDtoToOrder(OrderDto dto) {
        Order order = new Order();
        Product product = productService.getProduct(dto.getProduct().getId());
        List<Product> products = new ArrayList<>();
        products.add(product);
        order.setProducts(products);
        return order;
    }
}
