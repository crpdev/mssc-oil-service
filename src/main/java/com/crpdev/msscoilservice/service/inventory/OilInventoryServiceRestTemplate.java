package com.crpdev.msscoilservice.service.inventory;

import com.crpdev.msscoilservice.service.inventory.model.OilInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Profile("!local-discovery & !digitalocean")
@Slf4j
@ConfigurationProperties(prefix = "crp.factory", ignoreUnknownFields = true)
@Component
public class OilInventoryServiceRestTemplate implements OilInventoryService {

    public static final String INVENTORY_PATH = "/api/v1/oil/{oilId}/inventory";
    private final RestTemplate restTemplate;

    private String inventoryServiceHost;

    public OilInventoryServiceRestTemplate(RestTemplateBuilder restTemplateBuilder,
                                           @Value("${crp.factory.inventory-user}") String inventoryUser,
                                           @Value("${crp.factory.inventory-password}")String inventoryPassword) {
        this.restTemplate = restTemplateBuilder
                .basicAuthentication(inventoryUser, inventoryPassword)
                .build();
    }

    public void setInventoryServiceHost(String inventoryServiceHost){
        this.inventoryServiceHost = inventoryServiceHost;
    }

    @Override
    public Integer getOnHandInventory(UUID oilId) {
        log.debug("<<< Calling Inventory Service >>>");

        ResponseEntity<List<OilInventoryDto>> responseEntity = restTemplate.exchange(
                inventoryServiceHost + INVENTORY_PATH, HttpMethod.GET, null, new ParameterizedTypeReference<List<OilInventoryDto>>() {
                }, (Object) oilId
        );

        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(OilInventoryDto::getQuantityOnHand)
                .sum();

        return onHand;
    }
}
