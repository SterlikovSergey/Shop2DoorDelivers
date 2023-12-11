package st.sergey.minsky.shop2doordelivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.sergey.minsky.shop2doordelivers.model.Courier;
import st.sergey.minsky.shop2doordelivers.model.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderPepository extends JpaRepository<Order, Long> {

    Optional<Order> findOrderById(Long id);

    Optional<Order> findOrderByCourier(Courier courier);

    List<Order> findOrderByOrderStatusOrderByCreatedOrderDesc(String status);


}
