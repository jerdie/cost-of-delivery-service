package com.gerard.costofdeliveryservice.service;

import com.gerard.costofdeliveryservice.model.RequestDto;
import com.gerard.costofdeliveryservice.model.ResponseDto;
import com.gerard.costofdeliveryservice.model.VoucherDto;
import com.gerard.costofdeliveryservice.service.rules.ParcelRule;
import com.gerard.costofdeliveryservice.service.voucher.Voucher;
import com.gerard.costofdeliveryservice.service.voucher.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParcelCostService {

    private final List<? extends ParcelRule> parcelRules;
    private final VoucherService voucherService;

    @Value("${allowExpiredVoucher}")
    private boolean allowExpiredVoucher;

    public ResponseDto calculate(RequestDto requestDto) {
        Double cost = calculateParcelCost(requestDto);
        return applyVoucher(requestDto, cost);
    }

    public Double calculateParcelCost(RequestDto requestDto) {
        return parcelRules.stream()
                .filter(rule -> rule.condition(requestDto))
                .findFirst()
                .orElseThrow()
                .calculateCost(requestDto);
    }

    public ResponseDto applyVoucher(RequestDto requestDto, Double cost) {
        String message = "";
        double newCost = cost;

        try {
            if (StringUtils.hasText(requestDto.getVoucher())) {
                VoucherDto voucherInfo = voucherService.getVoucher(requestDto.getVoucher());
                Voucher voucher = new Voucher(voucherInfo, allowExpiredVoucher);
                newCost = voucher.applyTo(cost);
                message = "Discount: " + voucherInfo.getDiscount();
            }
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        return ResponseDto.builder()
                .cost(newCost)
                .message(message)
                .build();
    }

}
