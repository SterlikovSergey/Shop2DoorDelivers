package st.sergey.minsky.shop2doordelivers.mapper;

import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.StockDto;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.Stock;
import st.sergey.minsky.shop2doordelivers.model.Store;
@Component
public class StockMapper {
    public Stock stockDtoToStock(StockDto dto) {
        return Stock.builder()
                .product(Product
                        .builder()
                        .id(dto.getProductId())
                        .build())
                .store(Store
                        .builder()
                        .id(dto.getStoreId())
                        .build())
                .quantity(dto.getQuantity())
                .build();
    }
}
