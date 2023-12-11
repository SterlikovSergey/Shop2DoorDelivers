package st.sergey.minsky.shop2doordelivers.model;

import lombok.*;
import st.sergey.minsky.shop2doordelivers.model.enums.UserRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @OneToMany
    private List<Order> orders = new ArrayList<>();
}
