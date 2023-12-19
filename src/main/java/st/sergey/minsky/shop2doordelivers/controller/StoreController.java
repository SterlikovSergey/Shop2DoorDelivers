package st.sergey.minsky.shop2doordelivers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import st.sergey.minsky.shop2doordelivers.dto.ProductDto;
import st.sergey.minsky.shop2doordelivers.dto.StoreDto;
import st.sergey.minsky.shop2doordelivers.mapper.ProductMapper;
import st.sergey.minsky.shop2doordelivers.mapper.StoreMapper;
import st.sergey.minsky.shop2doordelivers.model.Store;
import st.sergey.minsky.shop2doordelivers.service.StoreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    private final StoreMapper storedMapper;

    private final ProductMapper productMapper;



    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        Store store = storeService.getStoreById(id);
        return ResponseEntity.ok(store);
    }

    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> stores = storeService.getAllStores();
        return ResponseEntity.ok(stores);
    }

    @PostMapping
    public ResponseEntity<Store> createOrUpdateStore(@RequestBody StoreDto store) {
        Store savedStore = storeService.createOrUpdateStore(storedMapper.storeDtoToStore(store));
        return ResponseEntity.ok(savedStore);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStoreById(@PathVariable Long id) {
        storeService.deleteStoreById(id);
        return ResponseEntity.noContent().build();
    }
}
