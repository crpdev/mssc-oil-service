package com.crpdev.msscoilservice.service;

import com.crpdev.msscoilservice.domain.Oil;
import com.crpdev.msscoilservice.repository.OilRepository;
import com.crpdev.msscoilservice.web.controller.NotFoundException;
import com.crpdev.msscoilservice.web.mapper.OilMapper;
import com.crpdev.msscoilservice.web.model.OilDto;
import com.crpdev.msscoilservice.web.model.OilPagedList;
import com.crpdev.msscoilservice.web.model.OilType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OilServiceImpl implements OilService {

    private final OilRepository oilRepository;
    private final OilMapper oilMapper;

    @Cacheable(cacheNames = "oilCache", key = "#oilId", condition = "#showInventoryOnHand == false")
    @Override
    public OilDto getOilById(UUID oilId, Boolean showInventoryOnHand) {

        log.debug("<<< getOilById was called >>>");
        if(showInventoryOnHand){
            return oilMapper.oilToOilDtoWithInventory(oilRepository.findById(oilId).orElseThrow(NotFoundException::new));
        } else {
            return oilMapper.oilToOilDto(oilRepository.findById(oilId).orElseThrow(NotFoundException::new));
        }
    }

    @Cacheable(cacheNames = "oilBarCodeCache", key = "#productCode", condition = "#showInventoryOnHand == false")
    @Override
    public OilDto getOilByProductCode(String productCode, Boolean showInventoryOnHand) {

        log.debug("<<< getOilByProductCode was called >>>");
        if(showInventoryOnHand){
            return oilMapper.oilToOilDtoWithInventory(oilRepository.findByProductCode(productCode));
        } else {
            return oilMapper.oilToOilDto(oilRepository.findByProductCode(productCode));
        }
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
        oil.setProductCode(oilDto.getProductCode());
        return oilMapper.oilToOilDto(oilRepository.save(oil));
    }

    @Cacheable(cacheNames = "oilListCache", condition = "#showInventoryOnHand == false")
    @Override
    public OilPagedList listOils(String oilName, OilType oilType, PageRequest pageRequest, Boolean showInventoryOnHand) {

        log.debug("<<< listOils was called >>>");
        OilPagedList oilPagedList;
        Page<Oil> oilPage;

        if (!StringUtils.isEmpty(oilName) && !StringUtils.isEmpty(oilType)) {
            oilPage = oilRepository.findAllByOilNameAndOilType(oilName, oilType, pageRequest);
        } else if (!StringUtils.isEmpty(oilName) && StringUtils.isEmpty(oilType)) {
            oilPage = oilRepository.findAllByOilName(oilName, pageRequest);
        } else if (StringUtils.isEmpty(oilName) && !StringUtils.isEmpty(oilType)) {
            oilPage = oilRepository.findAllByOilType(oilType, pageRequest);
        } else {
            oilPage = oilRepository.findAll(pageRequest);
        }

        if (showInventoryOnHand){
            oilPagedList = new OilPagedList(oilPage
                    .getContent()
                    .stream()
                    .map(oilMapper::oilToOilDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(oilPage.getPageable().getPageNumber(),
                                    oilPage.getPageable().getPageSize()),
                    oilPage.getTotalElements());
        } else {
            oilPagedList = new OilPagedList(oilPage
                    .getContent()
                    .stream()
                    .map(oilMapper::oilToOilDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(oilPage.getPageable().getPageNumber(),
                                    oilPage.getPageable().getPageSize()),
                    oilPage.getTotalElements());
        }

        return oilPagedList;

    }

}
