package st.sergey.minsky.shop2doordelivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.sergey.minsky.shop2doordelivers.model.Courier;
import st.sergey.minsky.shop2doordelivers.model.Order;
import st.sergey.minsky.shop2doordelivers.model.enums.OrderStatus;
import st.sergey.minsky.shop2doordelivers.repository.view.OrderView;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByStatus(OrderStatus status);

/*    List<Order> findAllByOrderStatusOrderByCreatedOrderDesc(OrderStatus status);

    List<Order> findOrderByCourier_Id(Long courierId);*/


    /*Optional<Order> findOrderById(Long id);*/

/*    Order findOrderById(Long id);

    Optional<Order> findOrderByCourier(Courier courier);*/

/*    List<Order> findOrderByOrderStatusOrderByCreatedOrderDesc(String status);*/


}
