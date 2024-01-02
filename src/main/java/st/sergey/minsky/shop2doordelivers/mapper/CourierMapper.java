package st.sergey.minsky.shop2doordelivers.mapper;

import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.CourierDto;
import st.sergey.minsky.shop2doordelivers.model.Courier;
@Component
public class CourierMapper {
    public Courier courierDtoToCoutier(CourierDto dto){
        Courier courier = new Courier();
        courier.setName(dto.getName());
        return courier;
    }
}
