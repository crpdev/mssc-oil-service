package com.crpdev.msscoilservice.service.inventory;

import com.crpdev.msscoilservice.service.inventory.model.OilInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by rajapandian
 * Date: 14/09/20
 * Project: mssc-oil-eureka
 * Package: com.crpdev.msscoilservice.service.inventory
 **/
@Profile({"local-discovery", "digitalocean"})
@Slf4j
@Service
@RequiredArgsConstructor
public class OilInventoryServiceFeign implements OilInventoryService {

    private final InventoryServiceFeignClient feignClient;

    @Override
    public Integer getOnHandInventory(UUID oilId) {
        log.debug("Calling Inventory Service via Feign for Oil Id: " + oilId);

        ResponseEntity<List<OilInventoryDto>> responseEntity = feignClient.getOnHandInventory(oilId);

        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(OilInventoryDto::getQuantityOnHand)
                .sum();
        log.debug("Oil Id: " + oilId + " On Hand: " + onHand);
        return onHand;
    }
}
