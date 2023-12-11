package st.sergey.minsky.shop2doordelivers.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import st.sergey.minsky.shop2doordelivers.model.enums.OrderStatus;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdOrder;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime deliveryTime;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany
    private List<Product> products = new ArrayList<>();

    @OneToOne
    private Payment payment;

    @ManyToOne
    private Courier courier;

    @ManyToOne
    private User user;
}