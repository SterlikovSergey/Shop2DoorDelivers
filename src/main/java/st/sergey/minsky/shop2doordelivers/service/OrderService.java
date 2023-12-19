package st.sergey.minsky.shop2doordelivers.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exception.OrderNotFoundException;
import st.sergey.minsky.shop2doordelivers.exception.UserNotFoundException;
import st.sergey.minsky.shop2doordelivers.model.Order;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.User;
import st.sergey.minsky.shop2doordelivers.model.enums.OrderStatus;
import st.sergey.minsky.shop2doordelivers.repository.OrderRepository;
import st.sergey.minsky.shop2doordelivers.repository.viev.OrderView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    public Order createOrder(Long userId) {
        User user = userService.findById(userId).orElseThrow(() -> new UserNotFoundException("User " +
                "not found with id " + userId));
        LocalDateTime currentDateTime = LocalDateTime.now();
        Order newOrder = Order.builder()
                .createdOrder(currentDateTime)
                .orderStatus(OrderStatus.CREATED)
                .user(user)
                .build();

        return orderRepository.save(newOrder);
    }

    public Order addProductToOrder(Long orderId, Product product) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException("Order not found"));
        Product balanceProduct =  productService.getProduct(product.getId());

        Short amountBalanceProduct = balanceProduct.getAmount();
        Short amountOrderProduct = product.getAmount();

        balanceProduct.setAmount((short) (amountBalanceProduct - amountOrderProduct));
        productService.update(balanceProduct);


        List<Product> existsProducts = order.getProducts();
        existsProducts.add(product);
        order.setProducts(existsProducts);

/*        BigDecimal totalCost = calculateTotalCost(existsProducts); // Подсчитываем общую стоимость товаров
        int totalItems = existsProducts.size(); // Получаем общее количество товаров

        order.setTotalCost(totalCost);
        order.setTotalCost(BigDecimal.valueOf(totalItems));*/

        return orderRepository.save(order);

    }

    private BigDecimal calculateTotalCost(List<Product> products) {
        BigDecimal totalCost = BigDecimal.ZERO;

        for (Product product : products) {
            BigDecimal price = product.getPrice();
            Short amount = product.getAmount();

            BigDecimal productTotalCost = price.multiply(BigDecimal.valueOf(amount));
            totalCost = totalCost.add(productTotalCost);
        }

        return totalCost;
    }

    public Order createOrUpdateOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<OrderView> getAllOrdersByStatus(OrderStatus status) {
        return orderRepository.findAllByOrderStatusOrderByCreatedOrderDesc(status);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
