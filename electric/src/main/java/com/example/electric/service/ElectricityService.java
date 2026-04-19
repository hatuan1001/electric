package com.example.electric.service;

import com.example.electric.DTO.CalculateBillRequest;
import com.example.electric.entity.ElectricityHistory;
import com.example.electric.entity.ElectricityTier;
import com.example.electric.entity.User;
import com.example.electric.repository.ElectricityHistoryRepository;
import com.example.electric.repository.ElectricityTierRepository;
import com.example.electric.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ElectricityService {
    @Autowired
    private ElectricityTierRepository electricityTierRepository;

    @Autowired
    private ElectricityHistoryRepository electricityHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    public ElectricityHistory calculateAndSaveBill(CalculateBillRequest request){
        User customer = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("không tìm thấy khách hàng"));

        List<ElectricityTier> tiers = electricityTierRepository.findAllByOrderByMinKwh();

        if(tiers.isEmpty()){
            throw new RuntimeException("Chưa có dữ liệu cấu hình bậc thang điện trong hệ thống");
        }

        double remainKwh = request.getKwhUsed();
        double totalCost = 0.0;

        for(ElectricityTier tier : tiers){
            if(remainKwh <= 0) break;

            double tierKwhUsed = 0;

            if(tier.getMaxKwh() != null){
                double tierCapacity = tier.getMaxKwh() - tier.getMinKwh();
                tierKwhUsed = Math.min(tierCapacity, remainKwh);
            } else{
                tierKwhUsed = remainKwh;
            }

            totalCost += (tierKwhUsed * tier.getPricePerKwh());

            remainKwh -= tierKwhUsed;
        }

        ElectricityHistory history = new ElectricityHistory();
        history.setUser(customer);
        history.setUsageDate(request.getUsageDate());
        history.setKwhUsed(request.getKwhUsed());
        history.setTotalAmount(totalCost);
        history.setCreatedAt(LocalDateTime.now());

        return electricityHistoryRepository.save(history);
    }
    public List<ElectricityHistory> getHistoryByUserId(String userId) {
        return electricityHistoryRepository.findByUserIdOrderByUsageDateDesc(userId);
    }
}
