package st.sergey.minsky.shop2doordelivers.model;

import lombok.*;
import st.sergey.minsky.shop2doordelivers.model.enums.CourierStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CourierStatus status;


    @OneToMany
    private List<Order> orders = new ArrayList<>();

    @OneToMany
    List<Comment> comments = new ArrayList<>();

    public String getStatus() {
        return this.status != null ? this.status.name() : null;
    }

}
