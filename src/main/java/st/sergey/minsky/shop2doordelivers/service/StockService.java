package st.sergey.minsky.shop2doordelivers.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.Stock;
import st.sergey.minsky.shop2doordelivers.model.Store;
import st.sergey.minsky.shop2doordelivers.repository.StockRepository;
import st.sergey.minsky.shop2doordelivers.repository.view.ProductStockView;

import java.util.Collections;
import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
public class StockService {
    public static final Logger LOG = LoggerFactory.getLogger(StockService.class);
    private final StockRepository stockRepository;
    private final ProductService productService;
    private final StoreService storeService;

    public Object createStock(Stock stock) {
        Product product = productService.getProduct(stock.getProduct().getId());
        Store store = storeService.getStoreById(stock.getStore().getId());
        Stock updatedStock = stockRepository.findStockByProductAndStore(product, store)
                .map(existingStock -> {
                    existingStock.setQuantity(existingStock.getQuantity() + stock.getQuantity());
                    return existingStock;
                })
                .orElseGet(() -> Stock
                        .builder()
                        .product(product)
                        .store(store)
                        .quantity(stock.getQuantity())
                        .build());

        updatedStock.setId(updatedStock.getId());

        LOG.info("The stock for the product {} in store {} has been updated, quantity {} }",
                updatedStock.getProduct().getName(),
                updatedStock.getStore().getName(),
                updatedStock.getQuantity());

        return stockRepository.save(updatedStock);
    }

    public List<ProductStockView> findAllProductStocksFromAllStoresWithPositiveQuantity() {
        return stockRepository.findAllProductStocksFromAllStores();
    }
    public Product findProductWithStockByMinAmount(Long productId){
        return stockRepository.findProductWithMinPriceInAllStores(productId);
    }
}





