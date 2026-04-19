package com.example.electric.repository;

import com.example.electric.entity.ElectricityHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricityHistoryRepository extends JpaRepository<ElectricityHistory, String> {
    List<ElectricityHistory> findByUserIdOrderByUsageDateDesc (String userId);
}
