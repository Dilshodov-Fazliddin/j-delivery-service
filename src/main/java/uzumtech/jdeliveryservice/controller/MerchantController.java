package uzumtech.jdeliveryservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uzumtech.jdeliveryservice.dto.request.MerchantRequest;
import uzumtech.jdeliveryservice.service.MerchantService;

@RestController
@RequestMapping("/api/delivery/merchant")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class MerchantController {

    MerchantService merchantService;

    @PostMapping
    public ResponseEntity<?> createMerchant(@RequestBody MerchantRequest merchantRequest){
        merchantService.saveMerchant(merchantRequest);
        return ResponseEntity.ok().body("Merchant Saved");
    }
}
