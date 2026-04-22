package com.example.electric.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class CalculateBillRequest {
    private String userId;
    private LocalDate usageDate;

    @NotNull
    @Positive(message = "số điện sử dụng không được âm")
    private Double kwhUsed;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(LocalDate usageDate) {
        this.usageDate = usageDate;
    }

    public Double getKwhUsed() {
        return kwhUsed;
    }

    public void setKwhUsed(Double kwhUsed) {
        this.kwhUsed = kwhUsed;
    }
}
