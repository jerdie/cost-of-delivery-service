package com.gerard.costofdeliveryservice.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
public class
RequestDto {
    @NotNull
    private Integer weight;
    @NotNull
    private Integer height;
    @NotNull
    private Integer width;
    @NotNull
    private Integer length;
    private String voucher;
}
