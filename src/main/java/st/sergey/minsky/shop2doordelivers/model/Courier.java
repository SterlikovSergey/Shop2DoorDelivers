package st.sergey.minsky.shop2doordelivers.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CourierStatus status;

    @OneToMany(mappedBy = "courier")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "courier")
    private List<Comment> comments = new ArrayList<>();
}
