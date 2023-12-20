package st.sergey.minsky.shop2doordelivers.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exception.StoreNotFoundException;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.repository.ProductRepository;

import java.util.List;

@Builder
@Service
@RequiredArgsConstructor
public class ProductService {

    public static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final StoreService storeService;


    public Product create(Product product) {
        LOG.info("Saving product " + product.getName());
        return productRepository.save(product);
    }

    public Product setAmountStore(Product product) {
        Product seveProduct = getProduct(product.getId());
        seveProduct.setAmount(product.getAmount());
        seveProduct.setPrice(product.getPrice());
        seveProduct.setStore(product.getStore());
        return productRepository.save(seveProduct);
    }

    public List<Product> readAll() {
        return productRepository.findAll();
    }

    public List<Product> readByCategoryId(Long id){
        return productRepository.findByCategoryId(id);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }



    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new StoreNotFoundException("Store with id " + productId + " not found"));
    }
}
