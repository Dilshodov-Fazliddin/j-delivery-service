package uzumtech.jdeliveryservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import uzumtech.jdeliveryservice.dto.request.BillRequest;
import uzumtech.jdeliveryservice.dto.response.BillResponse;
import uzumtech.jdeliveryservice.service.BillService;

@RestController
@RequestMapping("/api/delivery/bill")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillController {
    BillService billService;

    @GetMapping("/calculate")
    public BillResponse calculateBill(@RequestBody BillRequest billRequest)
    {
        return billService.calculateParcel(billRequest);
    }

}
