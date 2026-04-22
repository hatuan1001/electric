package com.example.electric.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class TierRequest {

    @NotNull()
    @PositiveOrZero(message = "minKwh phải >= 0")
    private Double minKwh;

    @DecimalMin(value = "0.0", message = "maxKwh phải > 0")
    private Double maxKwh;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "pricePerKwh phải > 0")
    private Double pricePerKwh;

    private String description;

    public Double getMinKwh() {
        return minKwh;
    }

    public void setMinKwh(Double minKwh) {
        this.minKwh = minKwh;
    }

    public Double getMaxKwh() {
        return maxKwh;
    }

    public void setMaxKwh(Double maxKwh) {
        this.maxKwh = maxKwh;
    }

    public Double getPricePerKwh() {
        return pricePerKwh;
    }

    public void setPricePerKwh(Double pricePerKwh) {
        this.pricePerKwh = pricePerKwh;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}