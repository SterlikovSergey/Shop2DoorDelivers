package st.sergey.minsky.shop2doordelivers.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exception.CategoryExistException;
import st.sergey.minsky.shop2doordelivers.exception.CourierExistException;
import st.sergey.minsky.shop2doordelivers.model.Courier;
import st.sergey.minsky.shop2doordelivers.model.Order;
import st.sergey.minsky.shop2doordelivers.model.enums.CourierStatus;
import st.sergey.minsky.shop2doordelivers.repository.CourierRepository;
import st.sergey.minsky.shop2doordelivers.utils.StringUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierService {
    public static final Logger LOG = LoggerFactory.getLogger(CourierService.class);
    private final CourierRepository courierRepository;
    private final StringUtil stringUtil;

    public Object create(Courier courier) {
        String capitalizedCourierName = stringUtil.capitalizeFirstLetter(courier.getName());
        return saveCourier(courier, capitalizedCourierName);
    }

    private Object saveCourier(Courier courier, String capitalizedCourierName) {
        try {
            LOG.info("Saving courier {}", capitalizedCourierName);
            return courierRepository.save(Courier
                    .builder()
                    .name(capitalizedCourierName)
                    .status(CourierStatus.READY)
                    .build());
        } catch (DataIntegrityViolationException e) {
            LOG.error("Error during courier. {}", e.getMessage());
            throw new CourierExistException("The courier " + capitalizedCourierName +
                    " already exist. Please check credentials");
        }
    }

    public List<Object> findAllCouriers() {
        return courierRepository.findAllBy();
    }
}
