package st.sergey.minsky.shop2doordelivers.mapper;

import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.StoreDto;
import st.sergey.minsky.shop2doordelivers.model.Store;
@Component
public class StoredMapper {
    public Store storeDtoToStore(StoreDto dto){
        Store store = new Store();
        store.setName(dto.getName());
        return store;
    }
}
