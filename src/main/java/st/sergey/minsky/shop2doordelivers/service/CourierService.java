package st.sergey.minsky.shop2doordelivers.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.model.Courier;
import st.sergey.minsky.shop2doordelivers.model.Order;
import st.sergey.minsky.shop2doordelivers.model.enums.CourierStatus;
import st.sergey.minsky.shop2doordelivers.repository.CourierRepository;

@Service
@RequiredArgsConstructor
public class CourierService {

    public static final Logger LOG = LoggerFactory.getLogger(CourierService.class);

    private final CourierRepository courierRepository;
    public Courier create(Courier courier) {
        LOG.info("Saving courier  " + courier.getName() + "assigned status READY");
        courier.setStatus(CourierStatus.READY);
        return courierRepository.save(courier);
    }





}
