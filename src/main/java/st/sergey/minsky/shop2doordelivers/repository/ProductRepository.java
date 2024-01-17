package st.sergey.minsky.shop2doordelivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.repository.view.ProductNameView;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long id);

    Optional<Product> findProductById(Long id);

/*    Optional<Product> findProductByIdAndStoreId(Long productId, Long storeId);*/

    Optional<Product> findByNameContainingIgnoreCase(String name);

/*    List<Product> findProductsByStoreIsNotNull();*/

    List<Product> findAllByNameContainingIgnoreCase(String fragment);

    boolean existsByName(String name);
}
