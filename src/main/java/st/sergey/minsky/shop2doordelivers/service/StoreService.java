package st.sergey.minsky.shop2doordelivers.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exception.StoreExistException;
import st.sergey.minsky.shop2doordelivers.exception.StoreNotFoundException;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.Store;
import st.sergey.minsky.shop2doordelivers.model.enums.StoreStatus;
import st.sergey.minsky.shop2doordelivers.repository.ProductRepository;
import st.sergey.minsky.shop2doordelivers.repository.StoreRepository;
import st.sergey.minsky.shop2doordelivers.repository.view.StoreView;
import st.sergey.minsky.shop2doordelivers.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StoreService {
    public static final Logger LOG = LoggerFactory.getLogger(StoreService.class);

    private final StoreRepository storeRepository;
    private final StringUtil stringUtil;

    public Object create(Store store) {
        String capitalizedStoreName = stringUtil.capitalizeFirstLetter(store.getName());
        if (isStoreNameUnique(capitalizedStoreName)) {
            return saveStore(store, capitalizedStoreName);
        } else {
            LOG.error("The store {} already exist. Please check credentials", capitalizedStoreName);
            throw new StoreExistException("The store "
                    + store.getName() + " already exist. Please check credentials");
        }
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElseThrow(() ->
                new StoreNotFoundException("Store with ID " + id + " not found."));
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

    public boolean isStoreNameUnique(String name) {
        return !storeRepository.existsByName(name);
    }

    private Object saveStore(Store store, String capitalizedStoreName) {
        try {
            LOG.info("Saving store {}", capitalizedStoreName);
            return storeRepository.save(Store
                    .builder()
                    .name(capitalizedStoreName)
                    .status(StoreStatus.CREATED)
                    .build());
        } catch (DataIntegrityViolationException e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new StoreExistException("The store " + capitalizedStoreName +
                    " already exist. Please check credentials");
        }
    }
}
