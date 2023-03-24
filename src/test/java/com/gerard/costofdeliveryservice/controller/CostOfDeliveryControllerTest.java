package com.gerard.costofdeliveryservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerard.costofdeliveryservice.model.RequestDto;
import com.gerard.costofdeliveryservice.service.ParcelCostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CostOfDeliveryController.class)
class CostOfDeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ParcelCostService parcelCostService;

    @Test
    void returns_200() throws Exception {
        var parcelDto = RequestDto.builder().weight(51).length(1).width(1).height(1).build();
        mockMvc.perform(post("/api/parcel/cost")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(parcelDto)))
                .andExpect(status().isOk());
    }

    @Test
    void returns_400() throws Exception {
        var requestDto = RequestDto.builder().weight(51).length(1).width(1).height(1).build();
        when(parcelCostService.calculate(any())).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(post("/api/parcel/cost")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(content().string("Error"))
                .andExpect(status().isBadRequest());
    }
}