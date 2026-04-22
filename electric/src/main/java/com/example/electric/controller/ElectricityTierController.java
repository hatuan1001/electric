package com.example.electric.controller;

import com.example.electric.DTO.TierRequest;
import com.example.electric.entity.ElectricityTier;
import com.example.electric.repository.ElectricityTierRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiers")
public class ElectricityTierController {

    @Autowired
    private ElectricityTierRepository electricityTierRepository;

    @GetMapping
    public ResponseEntity<List<ElectricityTier>> getAllTiers() {
        List<ElectricityTier> tiers = electricityTierRepository.findAllByOrderByMinKwh();
        return ResponseEntity.ok(tiers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElectricityTier> getTierById(@PathVariable String id) {
        ElectricityTier tier = electricityTierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tier với id: " + id));
        return ResponseEntity.ok(tier);
    }

    @PreAuthorize("hasAuthority('ROLE_ELECTRIAN')")
    @PostMapping
    public ResponseEntity<ElectricityTier> createTier(@Valid @RequestBody TierRequest request) {
        validateTierRange(request, null);

        ElectricityTier tier = new ElectricityTier();
        mapRequestToTier(request, tier);

        return ResponseEntity.ok(electricityTierRepository.save(tier));
    }

    @PreAuthorize("hasAuthority('ROLE_ELECTRIAN')")
    @PutMapping("/{id}")
    public ResponseEntity<ElectricityTier> updateTier(@PathVariable String id,
                                                      @Valid @RequestBody TierRequest request) {
        ElectricityTier tier = electricityTierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tier với id: " + id));

        validateTierRange(request, id);
        mapRequestToTier(request, tier);

        return ResponseEntity.ok(electricityTierRepository.save(tier));
    }

    @PreAuthorize("hasAuthority('ROLE_ELECTRIAN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTier(@PathVariable String id) {
        if (!electricityTierRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy tier với id: " + id);
        }
        electricityTierRepository.deleteById(id);
        return ResponseEntity.ok("Xóa tier thành công");
    }

    private void mapRequestToTier(TierRequest request, ElectricityTier tier) {
        tier.setMinKwh(request.getMinKwh());
        tier.setMaxKwh(request.getMaxKwh());
        tier.setPricePerKwh(request.getPricePerKwh());
        tier.setDescription(request.getDescription());
    }

    private void validateTierRange(TierRequest request, String excludeId) {
        if (request.getMaxKwh() != null && request.getMaxKwh() <= request.getMinKwh()) {
            throw new RuntimeException("maxKwh phải lớn hơn minKwh");
        }
    }
}