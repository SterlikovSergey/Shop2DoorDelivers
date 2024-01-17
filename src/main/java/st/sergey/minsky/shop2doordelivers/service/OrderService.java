package st.sergey.minsky.shop2doordelivers.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exception.CourierNotFoundException;
import st.sergey.minsky.shop2doordelivers.exception.OrderNotFoundException;
import st.sergey.minsky.shop2doordelivers.exception.UserNotFoundException;
import st.sergey.minsky.shop2doordelivers.model.Courier;
import st.sergey.minsky.shop2doordelivers.model.Order;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.User;
import st.sergey.minsky.shop2doordelivers.model.enums.CourierStatus;
import st.sergey.minsky.shop2doordelivers.model.enums.OrderStatus;
import st.sergey.minsky.shop2doordelivers.repository.CourierRepository;
import st.sergey.minsky.shop2doordelivers.repository.OrderRepository;
import st.sergey.minsky.shop2doordelivers.repository.view.OrderView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public Order createOrder(Long userId, Product product) {
        User user = userService.findById(userId).orElseThrow(() -> new UserNotFoundException("User " +
                "not found with id " + userId));

        List<Product> products = new ArrayList<>();
        Product addProduct = productService.getProduct(product.getId());
        /*addProduct.setAmount(changeQuantityProduct(product));*/
        products.add(addProduct);

        LocalDateTime currentDateTime = LocalDateTime.now();
        Order newOrder = Order.builder()
                .createdOrder(currentDateTime)
                .status(OrderStatus.CREATED)
                .user(user)
                /*.products(products)*/
                .build();
        return orderRepository.save(newOrder);
    }

    public Order addProductToExistingOrder(Long orderId, Product product) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException("Order not found"));

        Product balanceProduct =  productService.getProduct(product.getId());
        /*Short amountBalanceProduct = balanceProduct.getAmount();
        Short amountOrderProduct = product.getAmount();
        balanceProduct.setAmount((short) (amountBalanceProduct - amountOrderProduct));*/
        productService.update(balanceProduct);

        List<Product> existsProducts = order.getProducts();
        existsProducts.add(product);
        order.setProducts(existsProducts);
        return orderRepository.save(order);
    }

/*    public List<OrderView> getOrdersByCourierId(Long courierId){
        return orderRepository.findOrderByCourier_Id(courierId);
    }*/

    public Order assignCourierToOrder(Long orderId, Long courierId){
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new OrderNotFoundException("Order not found"));

        Courier courier = courierRepository.findById(courierId).orElseThrow(() ->
                new CourierNotFoundException("Courier not found"));

        Courier newCourier = Courier.builder()
                .id(courier.getId())
                .name(courier.getName())
                .status(CourierStatus.BUSY)
                .build();

        Order newOrder = Order.builder()
                .id(order.getId())
                .courier(newCourier)
                .createdOrder(order.getCreatedOrder())
                .status(OrderStatus.PROCESSING)
                .products(order.getProducts())
                .user(order.getUser())
                .build();

        return orderRepository.save(newOrder);
    }

    private BigDecimal calculateTotalCost(List<Product> products) {
        BigDecimal totalCost = BigDecimal.ZERO;

        for (Product product : products) {
            BigDecimal price = product.getPrice();
            /*Short amount = product.getAmount();*/

            /*BigDecimal productTotalCost = price.multiply(BigDecimal.valueOf(amount));*/
            /*totalCost = totalCost.add(productTotalCost);*/
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

/*    public List<OrderView> getAllByProcessing(OrderStatus status){
        return orderRepository.findAllByOrderByStatus(status);
    }*/

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }


    /*private Short changeQuantityProduct (Product product){
        Product balanceProduct = productService.getProduct(product.getId());
        balanceProduct.setAmount((short) (balanceProduct.getAmount() - product.getAmount()));
        productService.update(balanceProduct);
        return product.getAmount();

    }*/
}
