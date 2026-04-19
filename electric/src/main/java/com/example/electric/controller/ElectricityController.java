package com.example.electric.controller;

import com.example.electric.DTO.CalculateBillRequest;
import com.example.electric.entity.ElectricityHistory;
import com.example.electric.service.ElectricityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/history")
public class ElectricityController {
    @Autowired
    private ElectricityService electricityService;

    @PreAuthorize("hasAuthority('ROLE_ELECTRIAN')")
    @PostMapping("/calculate")
    public ResponseEntity<ElectricityHistory> calculateBill(@RequestBody CalculateBillRequest request) {
        try {
            ElectricityHistory result = electricityService.calculateAndSaveBill(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/my-history")
    public ResponseEntity<List<ElectricityHistory>> getMyHistory(){
        String currentUserId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<ElectricityHistory> history = electricityService.getHistoryByUserId(currentUserId);
        return ResponseEntity.ok(history);
    }
}
