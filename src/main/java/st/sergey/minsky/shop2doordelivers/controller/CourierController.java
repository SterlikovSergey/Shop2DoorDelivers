package st.sergey.minsky.shop2doordelivers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.sergey.minsky.shop2doordelivers.dto.CourierDto;
import st.sergey.minsky.shop2doordelivers.mapper.CourierMapper;
import st.sergey.minsky.shop2doordelivers.model.Courier;
import st.sergey.minsky.shop2doordelivers.repository.view.CourierView;
import st.sergey.minsky.shop2doordelivers.service.CourierService;
import st.sergey.minsky.shop2doordelivers.validations.ResponseErrorValidator;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/courier")
public class CourierController {

    private final CourierService courierService;
    private final CourierMapper courierMapper;
    private final ResponseErrorValidator responseErrorValidator;

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody CourierDto dto,
                                              BindingResult result){
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(result);
        if(ObjectUtils.isEmpty(errors)){
            return errors;
        }
        Courier courier = courierService.create(courierMapper.courierDtoToCoutier(dto));
        return ResponseEntity.ok(courier);
    }
}
