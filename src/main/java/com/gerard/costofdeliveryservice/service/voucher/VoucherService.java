package com.gerard.costofdeliveryservice.service.voucher;

import com.gerard.costofdeliveryservice.model.VoucherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VoucherService {
    @Value("${voucher.api.key}")
    private String key;
    private final RestTemplate voucherRestTemplate;

    public VoucherDto getVoucher(String code) {
        String url = "/{code}?key={key}";
        final Map<String, String> params = new HashMap<>();
        params.put("key", key);
        params.put("code", code);
        return voucherRestTemplate.getForObject(url, VoucherDto.class, params);
    }

}
