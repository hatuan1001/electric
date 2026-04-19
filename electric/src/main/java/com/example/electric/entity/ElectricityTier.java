package com.example.electric.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ElectricityTier {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String id;
    private Double minKwh;
    private Double maxKwh;
    private Double pricePerKwh;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
