package com.crpdev.msscoilservice.web.mapper;

import com.crpdev.factory.oil.model.OilDto;
import com.crpdev.msscoilservice.domain.Oil;
import com.crpdev.msscoilservice.service.inventory.OilInventoryService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class OilMapperDecorator implements OilMapper {

    private OilInventoryService oilInventoryService;
    private OilMapper oilMapper;

    @Autowired
    public void setOilInventoryService(OilInventoryService oilInventoryService) {
        this.oilInventoryService = oilInventoryService;
    }

    @Autowired
    public void setOilMapper(OilMapper oilMapper) {
        this.oilMapper = oilMapper;
    }

    @Override
    public OilDto oilToOilDto(Oil oil) {
        return oilMapper.oilToOilDto(oil);
    }

    @Override
    public OilDto oilToOilDtoWithInventory(Oil oil) {
        OilDto dto = oilMapper.oilToOilDto(oil);
        dto.setQuantityOnHand(oilInventoryService.getOnHandInventory(oil.getId()));
        return dto;
    }

    @Override
    public Oil oilDtoToOil(OilDto oilDto) {
        return oilMapper.oilDtoToOil(oilDto);
    }
}
