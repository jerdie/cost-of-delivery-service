package com.gerard.costofdeliveryservice.service.voucher;

import com.gerard.costofdeliveryservice.model.VoucherDto;

import java.time.LocalDate;

public class Voucher {
    private final VoucherDto voucherInfo;
    private final boolean allowExpiredVoucher;
    public Voucher(VoucherDto voucherInfo, boolean allowExpiredVoucher) {
        this.voucherInfo = voucherInfo;
        this.allowExpiredVoucher = allowExpiredVoucher;
    }

    public Double applyTo(Double cost){
        double newCost;
        if (voucherInfo.getExpiry().isBefore(LocalDate.now()) && !allowExpiredVoucher) {
            throw new RuntimeException("Expired Voucher");
        }
        if (voucherInfo.getDiscount() > cost) {
            newCost = 0d;
        } else {
            newCost = cost - voucherInfo.getDiscount();
        }
        return newCost;
    }
}
