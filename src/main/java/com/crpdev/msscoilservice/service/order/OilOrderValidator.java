package com.crpdev.msscoilservice.service.order;

import com.crpdev.factory.oil.model.OilOrderDto;
import com.crpdev.msscoilservice.repository.OilRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by rajapandian
 * Date: 02/09/20
 * Project: mssc-oil-inventory-service
 * Package: com.crpdev.msscoilservice.service.order
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class OilOrderValidator {

    private final OilRepository oilRepository;

    public Boolean validateOrder(OilOrderDto oilOrderDto){
        AtomicInteger oilNotFound = new AtomicInteger();

        oilOrderDto.getOilOrderLines().forEach(orderLine -> {
            if (null == oilRepository.findByProductCode(orderLine.getProductCode())){
                oilNotFound.incrementAndGet();
            }
        });

        return oilNotFound.get() == 0;
    }

}
