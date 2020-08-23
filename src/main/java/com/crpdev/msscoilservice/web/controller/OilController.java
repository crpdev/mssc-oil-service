package com.crpdev.msscoilservice.web.controller;

import com.crpdev.msscoilservice.service.OilService;
import com.crpdev.msscoilservice.web.model.OilDto;
import com.crpdev.msscoilservice.web.model.OilPagedList;
import com.crpdev.msscoilservice.web.model.OilType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class OilController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final OilService oilService;

    @GetMapping(produces = { "application/json" }, path = "oil")
    public ResponseEntity<OilPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                  @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                  @RequestParam(value = "oilName", required = false) String oilName,
                                                  @RequestParam(value = "oilType", required = false) OilType oilType,
                                                  @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        OilPagedList oilList = oilService.listOils(oilName, oilType, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(oilList, HttpStatus.OK);
    }

    @GetMapping("oil/{oilId}")
    public ResponseEntity<OilDto> getOilById(@PathVariable("oilId") UUID oilId,
                                             @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
    if (showInventoryOnHand == null){
        showInventoryOnHand = false;
    }

        return new ResponseEntity<>(oilService.getOilById(oilId, showInventoryOnHand), HttpStatus.OK);
    }

    @GetMapping("oil/barCode/{productCode}")
    public ResponseEntity<OilDto> getOilByBarCode(@PathVariable("productCode") String productCode,
                                             @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
        if (showInventoryOnHand == null){
            showInventoryOnHand = false;
        }

        return new ResponseEntity<>(oilService.getOilByProductCode(productCode, showInventoryOnHand), HttpStatus.OK);
    }

    @PostMapping("oil")
    public ResponseEntity saveNewOil(@RequestBody @Validated  OilDto oilDto){
        return new ResponseEntity(oilService.saveNewOil(oilDto), HttpStatus.CREATED);
    }

    @PutMapping("oil/{oilId}")
    public ResponseEntity updateOil(@PathVariable("oilId") UUID oilId, @RequestBody @Validated OilDto oilDto){
        return new ResponseEntity(oilService.updateOil(oilId, oilDto), HttpStatus.ACCEPTED);
    }
}
