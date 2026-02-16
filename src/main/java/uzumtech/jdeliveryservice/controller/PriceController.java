package uzumtech.jdeliveryservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uzumtech.jdeliveryservice.dto.request.PriceRequest;
import uzumtech.jdeliveryservice.dto.request.PriceUpdateRequest;
import uzumtech.jdeliveryservice.dto.response.PriceResponse;
import uzumtech.jdeliveryservice.service.PriceService;

@RestController
@RequestMapping("/api/delivery/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @PostMapping
    public PriceResponse create(@RequestBody @Valid PriceRequest priceRequest) {
        return priceService.create(priceRequest);
    }

    @PutMapping("/{id}")
    public void updateById(
            @PathVariable Long id,
            @RequestBody @Valid PriceUpdateRequest priceRequest
    ) {
        priceService.updateById(id, priceRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable @Valid Long id) {
        priceService.deletePriceById(id);
    }

    @GetMapping
    public Page<PriceResponse> findAll(Pageable pageable) {
        return priceService.findAllByPage(pageable);
    }

    @GetMapping("/deleted")
    public Page<PriceResponse> getDeleted(Pageable pageable) {
        return priceService.getDeletedPrices(pageable);
    }
}
