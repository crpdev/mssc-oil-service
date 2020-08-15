package com.crpdev.msscoilservice.web.controller;

import com.crpdev.msscoilservice.service.OilService;
import com.crpdev.msscoilservice.web.model.OilDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/oil")
public class OilController {

    private final OilService oilService;

    @GetMapping("/{oilId}")
    public ResponseEntity<OilDto> getOilById(@PathVariable("oilId") UUID oilId) {
        return new ResponseEntity<>(oilService.getOilById(oilId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewOil(@RequestBody @Validated  OilDto oilDto){
        return new ResponseEntity(oilService.saveNewOil(oilDto), HttpStatus.CREATED);
    }

    @PutMapping("/{oilId}")
    public ResponseEntity updateOil(@PathVariable("oilId") UUID oilId, @RequestBody @Validated OilDto oilDto){
        return new ResponseEntity(oilService.updateOil(oilId, oilDto), HttpStatus.ACCEPTED);
    }
}
