package st.sergey.minsky.shop2doordelivers.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductStoreDto {
    @NotNull(message = "Please enter amount")
    private Short amount;

    @NotNull(message = "Enter the cost of the product")
    private BigDecimal price;

    @NotNull(message = "Enter the store of the product")
    private Long storeId;


}
