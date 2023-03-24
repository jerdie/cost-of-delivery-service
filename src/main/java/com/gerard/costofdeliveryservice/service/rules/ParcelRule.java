package com.gerard.costofdeliveryservice.service.rules;

import com.gerard.costofdeliveryservice.model.RequestDto;

public interface ParcelRule {
    Double calculateCost(RequestDto requestDto);
    boolean condition(RequestDto requestDto);
}
