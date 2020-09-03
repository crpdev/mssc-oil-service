package com.crpdev.msscoilservice.service;

import com.crpdev.factory.oil.model.OilDto;
import com.crpdev.factory.oil.model.OilPagedList;
import com.crpdev.factory.oil.model.OilType;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface OilService {

    OilDto getOilById(UUID oilId, Boolean showInventoryOnHand);

    OilDto saveNewOil(OilDto oilDto);

    OilDto updateOil(UUID oilId, OilDto oilDto);

    OilPagedList listOils(String oilName, OilType oilType, PageRequest pageRequest, Boolean showInventoryOnHand);

    OilDto getOilByProductCode(String productCode, Boolean showInventoryOnHand);
}
