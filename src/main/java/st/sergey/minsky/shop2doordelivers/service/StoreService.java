package st.sergey.minsky.shop2doordelivers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exceptions.ProductNotFoundException;
import st.sergey.minsky.shop2doordelivers.exceptions.StoreNotFoundException;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.Store;
import st.sergey.minsky.shop2doordelivers.repository.ProductRepository;
import st.sergey.minsky.shop2doordelivers.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public Store addProductToStore(Long storeId, Long productId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with ID: " + storeId));
        List<Product> products = new ArrayList<>();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
        products.add(product);
        store.setProducts(products);
        return storeRepository.save(store);
    }


    public Store createOrUpdateStore(Store store) {
        return storeRepository.save(store);
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public void deleteStoreById(Long id) {
        storeRepository.deleteById(id);
    }
}
