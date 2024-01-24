package st.sergey.minsky.shop2doordelivers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import st.sergey.minsky.shop2doordelivers.mapper.ProductOrderMapper;
import st.sergey.minsky.shop2doordelivers.model.Order;
import st.sergey.minsky.shop2doordelivers.service.OrderService;
import st.sergey.minsky.shop2doordelivers.validations.ResponseErrorValidator;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@Validated
public class OrderController {

    private final OrderService orderService;
    private final ProductOrderMapper productOrderMapper;
    private final ResponseErrorValidator responseErrorValidator;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}
