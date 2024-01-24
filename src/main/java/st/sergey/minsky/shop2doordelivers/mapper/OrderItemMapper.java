package st.sergey.minsky.shop2doordelivers.mapper;

import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.OrderItemDto;
import st.sergey.minsky.shop2doordelivers.model.OrderItem;
import st.sergey.minsky.shop2doordelivers.model.Product;

@Component
public class OrderItemMapper {
    public OrderItem OrderItemDtoToOrderItem(OrderItemDto dto){
        return OrderItem
                .builder()
                .product(Product
                        .builder()
                        .id(dto.getProductId())
                        .build())
                .quantity(dto.getQuantity())
                .build();
    }
}
