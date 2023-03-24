package com.gerard.costofdeliveryservice.model;

import lombok.Data;

@Data
public class ErrorDto {
    private String error;

    public ErrorDto(String error) {
        this.error = error;
    }
}
