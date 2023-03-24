package com.gerard.costofdeliveryservice.service.rules;

import com.gerard.costofdeliveryservice.model.RequestDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractParcelRule implements ParcelRule {

    protected String name;

    private final RuleProperties ruleProperties;

    protected Double getPrice() {
        return ruleProperties.getPrices().get(name);
    }

    public static int getVolume(RequestDto requestDto) {
        return requestDto.getWidth() * requestDto.getHeight() * requestDto.getLength();
    }

}
