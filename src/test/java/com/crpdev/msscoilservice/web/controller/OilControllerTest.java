package com.crpdev.msscoilservice.web.controller;

import com.crpdev.msscoilservice.bootstrap.OilLoader;
import com.crpdev.msscoilservice.service.OilService;
import com.crpdev.msscoilservice.web.model.OilDto;
import com.crpdev.msscoilservice.web.model.OilType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OilController.class)
class OilControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OilService oilService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getOilById() throws Exception {
        given(oilService.getOilById(any())).willReturn(getValidOilDto());
        mockMvc.perform(get("/api/v1/oil/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewOil() throws Exception {

        OilDto oilDto = getValidOilDto();
        String oilDtoToJson = objectMapper.writeValueAsString(oilDto);
        given(oilService.saveNewOil(any())).willReturn(getValidOilDto());

        mockMvc.perform(post("/api/v1/oil/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(oilDtoToJson))
                .andExpect(status().isCreated());

    }

    @Test
    void updateOil() throws Exception {

        given(oilService.updateOil(any(), any())).willReturn(getValidOilDto());
        OilDto oilDto = getValidOilDto();
        String oilDtoToJson = objectMapper.writeValueAsString(oilDto);

        mockMvc.perform(put("/api/v1/oil/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(oilDtoToJson))
                .andExpect(status().isAccepted());
    }

    private OilDto getValidOilDto() {
        return OilDto.builder()
                .oilName("Gold Winner")
                .oilType(OilType.COCUNUT)
                .barCode(OilLoader.OIL_BARCODE_1)
                .price(new BigDecimal("100"))
                .quantityOnHand(200)
                .build();
    }
}