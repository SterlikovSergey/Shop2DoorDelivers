package st.sergey.minsky.shop2doordelivers.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Short amount;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

/*    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;*/

    @ManyToMany(mappedBy = "products")
    private List<Store> stores = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
