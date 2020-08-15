package com.crpdev.msscoilservice.service;

import com.crpdev.msscoilservice.web.model.OilDto;

import java.util.UUID;

public interface OilService {

    OilDto getOilById(UUID oilId);

    OilDto saveNewOil(OilDto oilDto);

    OilDto updateOil(UUID oilId, OilDto oilDto);
}
