package st.sergey.minsky.shop2doordelivers.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class OrderItemDto {

    @NotBlank(message = "Please enter product id")
    private Long productId;

    @NotBlank(message = "Please enter quantity product")
    private int quantity;
}
