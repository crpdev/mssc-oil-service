package com.crpdev.msscoilservice.service.inventory;

import com.crpdev.msscoilservice.bootstrap.OilLoader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@Disabled
@Profile("!local-discovery")
@SpringBootTest
class OilInventoryServiceRestTemplateTest {

    @MockBean
    OilInventoryService oilInventoryService;

    @Test
    @Disabled
    void getOnHandInventory(){
        given(oilInventoryService.getOnHandInventory(any())).willReturn(anyInt());
        Integer onHand = oilInventoryService.getOnHandInventory(OilLoader.OIL_1_UUID);
        System.out.println(onHand);
    }

}
