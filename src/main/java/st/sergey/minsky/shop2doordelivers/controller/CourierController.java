package st.sergey.minsky.shop2doordelivers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import st.sergey.minsky.shop2doordelivers.dto.CourierDto;
import st.sergey.minsky.shop2doordelivers.mapper.CourierMapper;
import st.sergey.minsky.shop2doordelivers.service.CourierService;
import st.sergey.minsky.shop2doordelivers.validations.ResponseErrorValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/courier")
public class CourierController {

    private final CourierService courierService;
    private final CourierMapper courierMapper;
    private final ResponseErrorValidator responseErrorValidator;

    @GetMapping
    public ResponseEntity<List<Object>> getAllCouriers(){
        return new ResponseEntity<>(courierService.findAllCouriers(),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody CourierDto dto,
                                              BindingResult result){
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(result);
        if(ObjectUtils.isEmpty(errors)){
            return errors;
        }
        return new ResponseEntity<>(courierService.create(courierMapper.courierDtoToCourier(dto)),
                HttpStatus.CREATED);
    }
}
