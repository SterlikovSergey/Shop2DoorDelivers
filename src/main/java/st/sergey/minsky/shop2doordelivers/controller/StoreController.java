package st.sergey.minsky.shop2doordelivers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import st.sergey.minsky.shop2doordelivers.dto.StoreDto;
import st.sergey.minsky.shop2doordelivers.mapper.ProductMapper;
import st.sergey.minsky.shop2doordelivers.mapper.StoreMapper;
import st.sergey.minsky.shop2doordelivers.model.Store;
import st.sergey.minsky.shop2doordelivers.model.enums.StoreStatus;
import st.sergey.minsky.shop2doordelivers.repository.view.StoreView;
import st.sergey.minsky.shop2doordelivers.service.StoreService;
import st.sergey.minsky.shop2doordelivers.validations.ResponseErrorValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/store")
public class StoreController {


    private final StoreService storeService;

    private final StoreMapper storedMapper;

    private final ProductMapper productMapper;
    private final ResponseErrorValidator responseErrorValidator;



    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        Store store = storeService.getStoreById(id);
        return ResponseEntity.ok(store);
    }

    @GetMapping
    public ResponseEntity<List<StoreView>> getAllStoresByCreated() {
        List<StoreView> stores = storeService.getAllStoresByStatus(StoreStatus.CREATED);
        return ResponseEntity.ok(stores);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody StoreDto store,
                                                     BindingResult result) {
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(result);
        if(result.hasErrors()){
            return errors;
        }
        Store savedStore = storeService.create(storedMapper.storeDtoToStore(store));
        return ResponseEntity.ok(savedStore);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Store> deleteStoreById(@PathVariable ("id") Long id) {
        Store store = storeService.setDeleteStatusById(id);
        return ResponseEntity.ok(store);
    }
}
