package com.crpdev.msscoilservice.web.mapper;

import com.crpdev.factory.oil.model.OilDto;
import com.crpdev.msscoilservice.domain.Oil;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
@DecoratedWith(OilMapperDecorator.class)
public interface OilMapper {

    OilDto oilToOilDto(Oil oil);

    OilDto oilToOilDtoWithInventory(Oil oil);

    Oil oilDtoToOil(OilDto oilDto);

}
