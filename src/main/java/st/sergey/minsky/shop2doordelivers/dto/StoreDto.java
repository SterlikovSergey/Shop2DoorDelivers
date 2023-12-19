package st.sergey.minsky.shop2doordelivers.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class StoreDto {

    @NotBlank(message = "Please enter store name")
    private String name;
}
