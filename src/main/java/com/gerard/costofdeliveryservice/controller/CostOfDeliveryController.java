package com.gerard.costofdeliveryservice.controller;

import com.gerard.costofdeliveryservice.model.RequestDto;
import com.gerard.costofdeliveryservice.model.ResponseDto;
import com.gerard.costofdeliveryservice.service.ParcelCostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/parcel")
@RequiredArgsConstructor
public class CostOfDeliveryController {
    private final ParcelCostService parcelCostService;

    @GetMapping("/cost")
    public ResponseDto calculateCost(@Valid RequestDto requestDto) {
        return parcelCostService.calculate(requestDto);
    }
}
