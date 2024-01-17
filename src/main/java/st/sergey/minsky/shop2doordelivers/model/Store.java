package st.sergey.minsky.shop2doordelivers.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import st.sergey.minsky.shop2doordelivers.model.enums.StoreStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private StoreStatus status;

    @OneToMany(mappedBy = "store")
    private List<Stock> stocks = new ArrayList<>();
}
