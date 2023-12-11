package st.sergey.minsky.shop2doordelivers.model;

import lombok.*;
import st.sergey.minsky.shop2doordelivers.model.enums.PaymentStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private PaymentStatus status;

    @OneToOne
    private Order order;

}
