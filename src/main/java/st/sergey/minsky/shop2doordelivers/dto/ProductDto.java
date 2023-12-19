package st.sergey.minsky.shop2doordelivers.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductDto {

    @NotBlank(message = "Please enter product name")
    private String name;

    @NotNull(message = "Please enter product category ")
    private Long categoryId;
}
