package com.gerard.costofdeliveryservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class VoucherDto {
    private String code;
    private Double discount;
    private LocalDate expiry;
}
