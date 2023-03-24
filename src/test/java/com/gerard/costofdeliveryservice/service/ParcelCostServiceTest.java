package com.gerard.costofdeliveryservice.service;

import com.gerard.costofdeliveryservice.model.RequestDto;
import com.gerard.costofdeliveryservice.model.ResponseDto;
import com.gerard.costofdeliveryservice.service.rules.RuleProperties;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParcelCostServiceTest {

    @Autowired
    ParcelCostService parcelCostService;

    @AlwaysNull

    @Autowired
    private RuleProperties ruleProperties;

    @Test
    void calculate_reject() {
        RequestDto requestDto = RequestDto.builder().weight(51).length(1).width(1).height(1).build();
        Exception exception = assertThrows(RuntimeException.class, () -> parcelCostService.calculate(requestDto));
        String message = exception.getMessage();
        assertEquals("Weight exceeds limit", message);
    }

    @Test
    void calculate_heavy() {
        int weight = 11; // more than 10
        RequestDto requestDto = RequestDto.builder().weight(weight).length(1).width(1).height(1).build();
        ResponseDto response = parcelCostService.calculate(requestDto);
        assert ruleProperties != null;
        assertEquals(ruleProperties.getPrices().get("heavy") * weight, response.getCost());
    }

    @Test
    void calculate_small() {
        int height = 10;
        int length = 50;
        int width = 2;
        int volume = height * width * length;
        RequestDto requestDto = RequestDto.builder().weight(5).length(length).width(width).height(height).build();
        ResponseDto response = parcelCostService.calculate(requestDto);
        assert ruleProperties != null;
        assertEquals(volume * ruleProperties.getPrices().get("small"), response.getCost());
    }

    @Test
    void calculate_medium() {
        int height = 15;
        int length = 55;
        int width = 2;
        RequestDto requestDto = RequestDto.builder().weight(5).length(length).width(width).height(height).build();
        ResponseDto response = parcelCostService.calculate(requestDto);
        int volume = height * width * length;
        assert ruleProperties != null;
        assertEquals(volume * ruleProperties.getPrices().get("medium"), response.getCost());
    }

    @Test
    void calculate_large() {
        int height = 15;
        int length = 100;
        int width = 2;
        int volume = height * width * length;
        RequestDto requestDto = RequestDto.builder().weight(5).length(length).width(width).height(height).build();
        ResponseDto response = parcelCostService.calculate(requestDto);
        assert ruleProperties != null;
        assertEquals(volume * ruleProperties.getPrices().get("large"), response.getCost());
    }

    @Test
    void calculate_large_with_voucher() {
        int height = 15;
        int length = 100;
        int width = 2;
        int volume = height * width * length;
        RequestDto requestDto = RequestDto.builder().weight(5).length(length).width(width).height(height)
                .voucher("MYNT").build();
        ResponseDto response = parcelCostService.calculate(requestDto);
        assert ruleProperties != null;
        assertEquals(volume * ruleProperties.getPrices().get("large") - 12.25, response.getCost());
    }

    @Test
    void calculate_large_with_invalid_voucher() {
        int height = 15;
        int length = 100;
        int width = 2;
        int volume = height * width * length;
        RequestDto requestDto = RequestDto.builder().weight(5).length(length).width(width).height(height)
                .voucher("sdklks").build();
        ResponseDto response = parcelCostService.calculate(requestDto);
        assert ruleProperties != null;
        assertEquals(volume * ruleProperties.getPrices().get("large"), response.getCost());
    }
}