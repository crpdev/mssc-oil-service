package com.crpdev.msscoilservice.service;

import com.crpdev.msscoilservice.domain.Oil;
import com.crpdev.msscoilservice.repository.OilRepository;
import com.crpdev.msscoilservice.web.controller.NotFoundException;
import com.crpdev.msscoilservice.web.mapper.OilMapper;
import com.crpdev.msscoilservice.web.model.OilDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OilServiceImpl implements OilService {

    private final OilRepository oilRepository;
    private final OilMapper oilMapper;


    @Override
    public OilDto getOilById(UUID oilId) {
        return oilMapper.oilToOilDto(
                oilRepository.findById(oilId).orElseThrow(NotFoundException::new));
    }

    @Override
    public OilDto saveNewOil(OilDto oilDto) {
        return oilMapper.oilToOilDto(
                oilRepository.save(oilMapper.oilDtoToOil(oilDto)));
    }

    @Override
    public OilDto updateOil(UUID oilId, OilDto oilDto) {

        Oil oil = oilRepository.findById(oilId).orElseThrow(NotFoundException::new);
        oil.setOilName(oilDto.getOilName());
        oil.setOilType(oilDto.getOilType().name());
        oil.setPrice(oilDto.getPrice());
        oil.setBarCode(oilDto.getBarCode());
        return oilMapper.oilToOilDto(oilRepository.save(oil));
    }
}
