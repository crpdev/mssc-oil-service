package com.crpdev.msscoilservice.service.inventory;

import com.crpdev.msscoilservice.service.inventory.model.OilInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

/**
 * Created by rajapandian
 * Date: 14/09/20
 * Project: mssc-oil-eureka
 * Package: com.crpdev.msscoilservice.service.inventory
 **/
@FeignClient(name = "inventory-service")
public interface InventoryServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = OilInventoryServiceRestTemplate.INVENTORY_PATH)
    ResponseEntity<List<OilInventoryDto>> getOnHandInventory(@PathVariable UUID oilId);

}
