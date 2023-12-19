package st.sergey.minsky.shop2doordelivers.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exception.StoreNotFoundException;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.Store;
import st.sergey.minsky.shop2doordelivers.repository.ProductRepository;
import st.sergey.minsky.shop2doordelivers.repository.StoreRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public static final Logger LOG = LoggerFactory.getLogger(StoreService.class);





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
