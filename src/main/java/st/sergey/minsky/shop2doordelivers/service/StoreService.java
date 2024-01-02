package st.sergey.minsky.shop2doordelivers.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exception.StoreExistException;
import st.sergey.minsky.shop2doordelivers.exception.StoreNotFoundException;
import st.sergey.minsky.shop2doordelivers.exception.UserExistException;
import st.sergey.minsky.shop2doordelivers.exception.UserNotFoundException;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.Store;
import st.sergey.minsky.shop2doordelivers.model.User;
import st.sergey.minsky.shop2doordelivers.model.enums.StoreStatus;
import st.sergey.minsky.shop2doordelivers.repository.ProductRepository;
import st.sergey.minsky.shop2doordelivers.repository.StoreRepository;
import st.sergey.minsky.shop2doordelivers.repository.view.StoreView;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StoreService {
    public static final Logger LOG = LoggerFactory.getLogger(StoreService.class);

    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public Store create(Store store) {
        Store newStore = new Store();
        newStore.setName(store.getName().toLowerCase());
        newStore.setStatus(StoreStatus.CREATED);
        try {
            LOG.info("Saving store {}", newStore.getName());
            return storeRepository.save(newStore);
        } catch (DataIntegrityViolationException e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw  new StoreExistException("The store " + newStore.getName() + " already exist. Please check credentials");
        }
    }



    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }
    public List<StoreView> getAllStoresByStatus(StoreStatus status) {
        return storeRepository.findAllByStatus(status);
    }
    public Store setDeleteStatusById(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(() ->
                new StoreNotFoundException("Store with ID " + id + " not found."));
            LOG.info("Deleting store: " + store.getName());

            store.setStatus(StoreStatus.DELETED);
            return storeRepository.save(store);
    }

    public Store getStoreByName(String name) {
        Optional<Store> byName = storeRepository.findByName(name);
        return byName.orElseThrow(StoreExistException::new);
    }


}
