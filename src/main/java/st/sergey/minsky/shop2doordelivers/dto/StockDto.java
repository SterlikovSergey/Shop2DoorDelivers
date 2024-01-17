package st.sergey.minsky.shop2doordelivers.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@ToString
public class StockDto {

    @NotNull(message = "Please enter quantity")
    private Long quantity;

    @NotNull(message = "Please enter product id")
    private Long productId;

    @NotNull(message = "Please enter store id")
    private Long storeId;
}
