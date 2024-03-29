package st.sergey.minsky.shop2doordelivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.Stock;
import st.sergey.minsky.shop2doordelivers.model.Store;
import st.sergey.minsky.shop2doordelivers.repository.view.ProductStockView;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock,Long> {
    Optional<Stock> findStockByProductAndStore(Product product,Store store);

    @Query("SELECT p.id AS productId, p.name AS productName, p.price AS productPrice, s.quantity AS stockQuantity,st.id AS storeId, st.name AS storeName FROM Product p JOIN p.stocks s JOIN s.store st " +
            "WHERE s.quantity > 0")
    List<ProductStockView> findAllProductStocksFromAllStores();

    @Query("SELECT s.product FROM Stock s WHERE s.product.id = :productId AND s.quantity > 0 AND s.product.price = (SELECT MIN(s.product.price) FROM Stock s WHERE s.product.id = :productId AND s.quantity > 0)")
    Product findProductWithMinPriceInAllStores(@Param("productId") Long productId);

}
