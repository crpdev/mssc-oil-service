package com.crpdev.msscoilservice.service.order;

import com.crpdev.factory.oil.model.events.ValidateOrderRequest;
import com.crpdev.factory.oil.model.events.ValidateOrderResult;
import com.crpdev.msscoilservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by rajapandian
 * Date: 02/09/20
 * Project: mssc-oil-inventory-service
 * Package: com.crpdev.msscoilservice.service.order
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class OilOrderValidatorListener {

    private final OilOrderValidator oilOrderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest request){
        Boolean isValid = oilOrderValidator.validateOrder(request.getOilOrderDto());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_RESPONSE_QUEUE, ValidateOrderResult.builder()
                    .isValid(isValid)
                    .orderId(request.getOilOrderDto().getId())
                    .build());
    }

}
