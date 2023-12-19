package st.sergey.minsky.shop2doordelivers.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.ProductStoreDto;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.Store;
import st.sergey.minsky.shop2doordelivers.service.StoreService;
@Component
@RequiredArgsConstructor
public class ProductStoreMapper {

    private final StoreService storeService;

    public Product productStoreDtoToProduct(ProductStoreDto dto) {
        Product product = new Product();
        product.setAmount(dto.getAmount());
        product.setPrice(dto.getPrice());
        Store store = storeService.getStoreById(dto.getStoreId());
        product.setStore(store);
        return product;
    }
}

