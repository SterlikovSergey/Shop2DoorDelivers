package st.sergey.minsky.shop2doordelivers.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ProductOrderDto {

    @NotNull(message = "Please enter product id ")
    private Long id;

    @NotNull(message = "Please enter amount products ")
    private Short amount;
}
