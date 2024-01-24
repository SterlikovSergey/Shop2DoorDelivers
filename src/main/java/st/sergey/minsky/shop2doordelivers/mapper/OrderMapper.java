package st.sergey.minsky.shop2doordelivers.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.OrderDto;
import st.sergey.minsky.shop2doordelivers.model.Order;
import st.sergey.minsky.shop2doordelivers.model.Product;

import java.util.Collections;


@Component
@RequiredArgsConstructor
public class OrderMapper {
/*    public Order orderDtoToOrder(OrderDto dto) {
        return Order.builder()
                .products(Collections.singletonList(Product.builder()
                        .id(dto.getProduct().getId())
                        .build()))
                .build();
    }*/
}
