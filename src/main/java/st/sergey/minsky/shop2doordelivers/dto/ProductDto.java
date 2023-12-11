package st.sergey.minsky.shop2doordelivers.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductDto {
    private String name;
    private Short amount;
    private BigDecimal price;
    private Long categoryId;
}
