package com.gerard.costofdeliveryservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseDto {
    private Double cost;
    @Builder.Default
    private String message = "";
}
