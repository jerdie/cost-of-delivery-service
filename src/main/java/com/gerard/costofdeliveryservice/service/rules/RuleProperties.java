package com.gerard.costofdeliveryservice.service.rules;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "rule")
@Data
public class RuleProperties {
    private Map<String, Double> prices;
}
