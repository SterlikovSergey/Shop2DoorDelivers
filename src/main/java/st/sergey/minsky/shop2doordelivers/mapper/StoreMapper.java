package st.sergey.minsky.shop2doordelivers.mapper;

import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.StoreDto;
import st.sergey.minsky.shop2doordelivers.model.Store;

@Component
public class StoreMapper {
    public Store storeDtoToStore(StoreDto dto){
        return Store.builder()
                .name(dto.getName())
                .build();
    }
}
