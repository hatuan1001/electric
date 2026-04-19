package com.example.electric.DTO;

import java.time.LocalDate;

public class CalculateBillRequest {
    private String userId;
    private LocalDate usageDate;
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
