package com.crpdev.msscoilservice.service.inventory;

import com.crpdev.msscoilservice.service.inventory.model.OilInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by rajapandian
 * Date: 14/09/20
 * Project: mssc-oil-eureka
 * Package: com.crpdev.msscoilservice.service.inventory
 **/
@RequiredArgsConstructor
@Component
public class InventoryServiceFeignClientFailover implements InventoryServiceFeignClient {

    private final InventoryFailoverFeignClient feignClient;

    @Override
    public ResponseEntity<List<OilInventoryDto>> getOnHandInventory(UUID oilId) {
        return feignClient.getOnHandInventory(oilId);
    }
}
