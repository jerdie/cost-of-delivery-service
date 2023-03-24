package com.gerard.costofdeliveryservice.service.rules.parcel;

import com.gerard.costofdeliveryservice.model.RequestDto;
import com.gerard.costofdeliveryservice.service.rules.AbstractParcelRule;
import com.gerard.costofdeliveryservice.service.rules.RuleProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class Medium extends AbstractParcelRule {

    public Medium(RuleProperties ruleProperties) {
        super(ruleProperties);
        this.name = this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public boolean condition(RequestDto requestDto) {
        return getVolume(requestDto) < 2500;
    }

    @Override
    public Double calculateCost(RequestDto requestDto) {
        return getPrice() * getVolume(requestDto);
    }
}
