package uzumtech.jdeliveryservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzumtech.jdeliveryservice.dto.request.MerchantRequest;
import uzumtech.jdeliveryservice.dto.response.MerchantResponse;
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

    @GetMapping("/info/{name}")
    public ResponseEntity<MerchantResponse>getMerchantInfoByName(@PathVariable String  name){
        MerchantResponse merchantInfoByName = merchantService.getMerchantInfoByName(name);
        return ResponseEntity.ok(merchantInfoByName);
    }
}
