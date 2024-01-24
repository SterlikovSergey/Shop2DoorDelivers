package st.sergey.minsky.shop2doordelivers.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exception.UserNotFoundException;
import st.sergey.minsky.shop2doordelivers.model.*;
import st.sergey.minsky.shop2doordelivers.model.enums.OrderStatus;
import st.sergey.minsky.shop2doordelivers.repository.CourierRepository;
import st.sergey.minsky.shop2doordelivers.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class OrderService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final CourierRepository courierRepository;
    private final StockService stockService;

    public Order createOrder(Long userId, Long productId, int quantity) {

        User user = userService.findById(userId).orElseThrow(() -> new UserNotFoundException("User " +
                "not found with id " + userId));

        Product product = stockService.findProductWithStockByMinAmount(productId);

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);

        if(isUserIdAndStatusCreateUnique(userId,OrderStatus.CREATED)){
            return Order
                    .builder()
                    .createdOrder(LocalDateTime.now())
                    .user(user)
                    .status(OrderStatus.CREATED)
                    .build();
        }


        LocalDateTime currentDateTime = LocalDateTime.now();

        return orderRepository.save(Order.builder()
                .createdOrder(currentDateTime)
                .status(OrderStatus.CREATED)
                .user(user)
                /*.products(products)*/
                .build()
                );

    }
    public boolean isUserIdAndStatusCreateUnique(Long userId, OrderStatus status) {
        return !orderRepository.existsByUserAndStatus(userId,status);
    }

    private BigDecimal calculateTotalCost(List<Product> products) {
        BigDecimal totalCost = BigDecimal.ZERO;

        for (Product product : products) {
            BigDecimal price = product.getPrice();
        }
        return totalCost;
    }

    public Order createOrUpdateOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getAllOrdersByStatus(OrderStatus status) {
        return orderRepository.findAllByStatus(status);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
