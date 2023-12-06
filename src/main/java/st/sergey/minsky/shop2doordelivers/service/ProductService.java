package st.sergey.minsky.shop2doordelivers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public Product create(Product product) {
        return productRepository.save(product);
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
}
