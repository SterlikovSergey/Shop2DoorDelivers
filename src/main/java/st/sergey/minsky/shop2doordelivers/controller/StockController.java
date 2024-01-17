package st.sergey.minsky.shop2doordelivers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import st.sergey.minsky.shop2doordelivers.dto.StockDto;
import st.sergey.minsky.shop2doordelivers.mapper.StockMapper;
import st.sergey.minsky.shop2doordelivers.model.Stock;
import st.sergey.minsky.shop2doordelivers.repository.view.ProductStockView;
import st.sergey.minsky.shop2doordelivers.service.StockService;
import st.sergey.minsky.shop2doordelivers.validations.ResponseErrorValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stock")
public class StockController {
    private final ResponseErrorValidator responseErrorValidator;
    private final StockService stockService;
    private final StockMapper stockMapper;

    @PostMapping
    public Object create(@Valid @RequestBody StockDto dto, BindingResult result){
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(result);
        if(!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        return new ResponseEntity<>(stockService.createStock(stockMapper.stockDtoToStock(dto)),
                HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ProductStockView>> viewAllProductsFromAllStores(){
        return ResponseEntity.ok(stockService.findAllProductStocksFromAllStoresWithPositiveQuantity());
    }
}
