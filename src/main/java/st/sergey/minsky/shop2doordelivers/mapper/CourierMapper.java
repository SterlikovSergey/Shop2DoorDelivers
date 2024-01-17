package st.sergey.minsky.shop2doordelivers.mapper;

import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.CourierDto;
import st.sergey.minsky.shop2doordelivers.model.Courier;
@Component
public class CourierMapper {
    public Courier courierDtoToCourier(CourierDto dto){
        return Courier
                .builder()
                .name(dto.getName())
                .build();
    }
}
