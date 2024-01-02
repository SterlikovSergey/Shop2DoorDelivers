package st.sergey.minsky.shop2doordelivers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import st.sergey.minsky.shop2doordelivers.dto.ProductOrderDto;
import st.sergey.minsky.shop2doordelivers.mapper.ProductOrderMapper;
import st.sergey.minsky.shop2doordelivers.model.Order;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.enums.OrderStatus;
import st.sergey.minsky.shop2doordelivers.repository.view.OrderView;
import st.sergey.minsky.shop2doordelivers.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final ProductOrderMapper productOrderMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

/*
    @GetMapping("/all/create")
    public ResponseEntity<List<Order>> getAllOrdersByStatusCreate() {
        List<Order> orders = orderService.getAllOrdersByStatus(OrderStatus.CREATED);
        return ResponseEntity.ok(orders);
    }
*/

/*    @GetMapping("/all/processing")
    public ResponseEntity<List<OrderView>> getAllOrdersByStatusProcessing() {
        List<OrderView> orders = orderService.getAllByProcessing(OrderStatus.PROCESSING);
        return ResponseEntity.ok(orders);
    }*/

    @PostMapping("/{id}")
    public ResponseEntity<Order> create(@PathVariable ("id") Long userId) {
        Order savedOrder = orderService.createOrder(userId);
        return ResponseEntity.ok(savedOrder);
    }

    @PostMapping("/add-product/{orderId}")
    public ResponseEntity<Order> addProduct(@PathVariable ("orderId") Long orderId,
                                            @RequestBody ProductOrderDto dto){
        Product sevedProduct = productOrderMapper.productOrderDtoToProduct(dto);
        return new ResponseEntity<>(orderService.addProductToExistingOrder(orderId,sevedProduct), HttpStatus.CREATED);
    }

    @PostMapping("{orderId}/add-courier/{courierId}")
    public ResponseEntity<Order> addCourier(@PathVariable ("orderId") Long orderId,
                                                @PathVariable ("courierId") Long courierId){
        Order order = orderService.assignCourierToOrder(orderId,courierId);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}
