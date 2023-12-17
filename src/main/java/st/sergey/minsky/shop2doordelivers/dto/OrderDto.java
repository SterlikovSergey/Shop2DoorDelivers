package st.sergey.minsky.shop2doordelivers.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import st.sergey.minsky.shop2doordelivers.model.Product;

@Getter
@Setter
@ToString
public class OrderDto {
    private Product product;

}
