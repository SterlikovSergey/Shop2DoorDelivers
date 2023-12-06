package st.sergey.minsky.shop2doordelivers.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class ProductDto {
    private String name;
    private Short amount;
    private Long categoryId;
}
