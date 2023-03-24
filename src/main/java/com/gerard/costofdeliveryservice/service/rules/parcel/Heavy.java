package com.gerard.costofdeliveryservice.service.rules.parcel;

import com.gerard.costofdeliveryservice.model.RequestDto;
import com.gerard.costofdeliveryservice.service.rules.AbstractParcelRule;
import com.gerard.costofdeliveryservice.service.rules.RuleProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class Heavy extends AbstractParcelRule {

    public Heavy(RuleProperties ruleProperties) {
        super(ruleProperties);
        this.name = this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public boolean condition(RequestDto requestDto) {
        return requestDto.getWeight() > 10;
    }

    @Override
    public Double calculateCost(RequestDto requestDto) {
        return getPrice() * requestDto.getWeight();
    }
}
