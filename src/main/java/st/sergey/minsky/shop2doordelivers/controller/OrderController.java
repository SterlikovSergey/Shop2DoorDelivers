package st.sergey.minsky.shop2doordelivers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import st.sergey.minsky.shop2doordelivers.dto.ProductOrderDto;
import st.sergey.minsky.shop2doordelivers.mapper.ProductOrderMapper;
import st.sergey.minsky.shop2doordelivers.model.Order;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.enums.OrderStatus;
import st.sergey.minsky.shop2doordelivers.repository.view.OrderView;
import st.sergey.minsky.shop2doordelivers.service.OrderService;
import st.sergey.minsky.shop2doordelivers.validations.ResponseErrorValidator;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<Object> create(@Valid @RequestBody ProductOrderDto dto, BindingResult result,
                                        @PathVariable ("id") Long userId) {
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(result);
        if(!ObjectUtils.isEmpty(errors)){
            return errors;
        }
        return new ResponseEntity<>(orderService.createOrder(userId,productOrderMapper.productOrderDtoToProduct(dto)),
                HttpStatus.CREATED);
    }

    @PostMapping("/add-product/{orderId}")
    public ResponseEntity<Order> addProduct(@PathVariable ("orderId") Long orderId,
                                            @RequestBody ProductOrderDto dto){
        Product sevedProduct = productOrderMapper.productOrderDtoToProduct(dto);
        return new ResponseEntity<>(orderService.createOrder(orderId,sevedProduct), HttpStatus.CREATED);
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
