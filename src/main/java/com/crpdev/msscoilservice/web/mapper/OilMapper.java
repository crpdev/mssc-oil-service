package com.crpdev.msscoilservice.web.mapper;

import com.crpdev.msscoilservice.domain.Oil;
import com.crpdev.msscoilservice.web.model.OilDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface OilMapper {

    OilDto oilToOilDto(Oil oil);

    Oil oilDtoToOil(OilDto oilDto);

}
