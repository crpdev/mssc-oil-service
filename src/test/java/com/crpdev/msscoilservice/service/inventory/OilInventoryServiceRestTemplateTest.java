package com.crpdev.msscoilservice.service.inventory;

import com.crpdev.msscoilservice.bootstrap.OilLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OilInventoryServiceRestTemplateTest {

    @Autowired
    OilInventoryService oilInventoryService;

    @Test
    void getOnHandInventory(){
        Integer onHand = oilInventoryService.getOnHandInventory(OilLoader.OIL_1_UUID);
        System.out.println(onHand);
    }

}