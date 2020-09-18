package com.crpdev.msscoilservice.service.inventory;

import com.crpdev.msscoilservice.bootstrap.OilLoader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@Disabled
@Profile("!local-discovery")
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
