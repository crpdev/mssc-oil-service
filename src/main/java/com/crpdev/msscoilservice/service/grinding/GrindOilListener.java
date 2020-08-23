package com.crpdev.msscoilservice.service.grinding;

import com.crpdev.common.events.GrindOilEvent;
import com.crpdev.common.events.NewInventoryEvent;
import com.crpdev.msscoilservice.config.JmsConfig;
import com.crpdev.msscoilservice.domain.Oil;
import com.crpdev.msscoilservice.repository.OilRepository;
import com.crpdev.msscoilservice.web.model.OilDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rajapandian
 * Date: 23/08/20
 * Project: mssc-oil-inventory-service
 * Package: com.crpdev.msscoilservice.service.grinding
 **/
@Slf4j
@RequiredArgsConstructor
@Service
public class GrindOilListener {

    private final OilRepository oilRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.GRINDING_REQUEST_QUEUE)
    public void listen(GrindOilEvent event){
        OilDto oilDto= event.getOilDto();
        Oil oil = oilRepository.getOne(oilDto.getId());
        oilDto.setQuantityOnHand(oil.getQuantityToGrind());
        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(oilDto);
        log.debug("Grinding oil: " + oil.getMinOnHand() + " > QOH:  " + oilDto.getQuantityOnHand());
        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
        log.debug("<<< Published Message to Inventory Queue >>>");
    }

}
