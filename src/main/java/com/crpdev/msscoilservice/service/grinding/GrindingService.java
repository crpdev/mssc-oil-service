package com.crpdev.msscoilservice.service.grinding;

import com.crpdev.common.events.GrindOilEvent;
import com.crpdev.msscoilservice.config.JmsConfig;
import com.crpdev.msscoilservice.domain.Oil;
import com.crpdev.msscoilservice.repository.OilRepository;
import com.crpdev.msscoilservice.service.inventory.OilInventoryService;
import com.crpdev.msscoilservice.web.mapper.OilMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rajapandian
 * Date: 23/08/20
 * Project: mssc-oil-inventory-service
 * Package: com.crpdev.msscoilservice.service
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class GrindingService {

    private final OilRepository oilRepository;
    private final JmsTemplate jmsTemplate;
    private final OilInventoryService oilInventoryService;
    private final OilMapper oilMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory(){
        List<Oil> oils = oilRepository.findAll();
        oils.forEach(oil -> {
            Integer inventoryOnHand = oilInventoryService.getOnHandInventory(oil.getId());
            log.debug("Minimum on Hand is: " + oil.getMinOnHand());
            log.debug("Inventory on Hand is: " + inventoryOnHand);

            if (oil.getMinOnHand() >= inventoryOnHand){
                jmsTemplate.convertAndSend(JmsConfig.GRINDING_REQUEST_QUEUE, new GrindOilEvent(oilMapper.oilToOilDto(oil)));
            }

        });

    }

}
