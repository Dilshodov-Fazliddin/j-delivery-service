package uzumtech.jdeliveryservice.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import uzumtech.jdeliveryservice.dto.request.AddressRequest;
import uzumtech.jdeliveryservice.dto.request.AddressUpdateRequest;
import uzumtech.jdeliveryservice.dto.response.AddressResponse;
import uzumtech.jdeliveryservice.service.AddressService;

@RestController
@RequestMapping("/api/delivery/addresses")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AddressController {

    AddressService addressService;

    @PostMapping("/{id}")
    public AddressResponse createAddress(
            @RequestBody @Valid AddressRequest addressRequest,
            @PathVariable @Valid Long id
    ) {
        return addressService.createAddress(addressRequest,id);
    }

    @PatchMapping("/{id}")
    public void updateAddressById(
            @PathVariable @Valid Long id,
            @RequestBody @Valid AddressUpdateRequest addressRequest) {

        addressService.updateAddressById(id, addressRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAddressById(@PathVariable @Valid Long id) {
        addressService.deleteAddressById(id);
    }

    @GetMapping("/consumer/{consumerId}")
    public AddressResponse getAddressByConsumerId(
            @PathVariable @Valid Long consumerId) {

        return addressService.getAddressByConsumerId(consumerId);
    }
}
