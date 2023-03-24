package com.gerard.costofdeliveryservice.service.voucher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class VoucherConfig {

    @Value("${voucher.api.url}")
    private String voucherUrl;

    @Bean
    RestTemplate voucherRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate = restTemplateBuilder
                .rootUri(voucherUrl)
                .build();
        restTemplate.setErrorHandler(new VoucherResponseErrorHandler());
        return restTemplate;
    }
}
