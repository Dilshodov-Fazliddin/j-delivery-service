package uzumtech.jdeliveryservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import uzumtech.jdeliveryservice.dto.request.AddressRequest;
import uzumtech.jdeliveryservice.dto.response.AddressResponse;
import uzumtech.jdeliveryservice.service.AddressService;

@RestController
@RequestMapping("/api/delivery/addresses")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AddressController {

    AddressService addressService;

    @PostMapping("/{id}")
    public AddressResponse createAddress(@RequestBody AddressRequest addressRequest, @PathVariable Long id) {
        return addressService.createAddress(addressRequest,id);
    }

    @PatchMapping("/{id}")
    public void updateAddressById(
            @PathVariable Long id,
            @RequestBody AddressRequest addressRequest) {

        addressService.updateAddressById(id, addressRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAddressById(@PathVariable Long id) {
        addressService.deleteAddressById(id);
    }

    @GetMapping("/consumer/{consumerId}")
    public AddressResponse getAddressByConsumerId(
            @PathVariable Long consumerId) {

        return addressService.getAddressByConsumerId(consumerId);
    }
}
