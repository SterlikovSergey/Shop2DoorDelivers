package st.sergey.minsky.shop2doordelivers.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exception.ProductExistException;
import st.sergey.minsky.shop2doordelivers.exception.StoreExistException;
import st.sergey.minsky.shop2doordelivers.exception.StoreNotFoundException;
import st.sergey.minsky.shop2doordelivers.model.Category;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.repository.ProductRepository;
import st.sergey.minsky.shop2doordelivers.utils.StringUtil;

import java.util.List;

@Builder
@Service
@RequiredArgsConstructor
public class ProductService {
    public static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final StoreService storeService;
    private final StringUtil stringUtil;

    public Object create(Product product) {
        String capitalizedProductName = stringUtil.capitalizeFirstLetter(product.getName());
        Category categoryProduct = categoryService.getCategoryById(product.getCategory().getId());
        if(isProductNameUnique(capitalizedProductName)){
            product.setCategory(categoryProduct);
            return saveProduct(product, capitalizedProductName);
        }LOG.error("The product {} already exist. Please check credentials", capitalizedProductName);
        throw new StoreExistException("The product "
                + product.getName() + " already exist. Please check credentials");

    }

    public List<Product> readByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }

    public Object update(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new StoreNotFoundException("Store with id " + productId + " not found"));
    }

    public boolean isProductNameUnique(String name) {
        return !productRepository.existsByName(name);
    }

    private Object saveProduct(Product product, String capitalizedProductName) {
        try {
            product.setName(capitalizedProductName);
            LOG.info("Saving product {} ", product.getName());
            return productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            LOG.error("CATCH Error during registration. {}", e.getMessage());
            throw new ProductExistException("The product  with name " + product.getName() +
                    " already exists. Please check credentials");
        }
    }
}
