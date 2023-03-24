package com.gerard.costofdeliveryservice.service.voucher;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ServerErrorException;

import java.io.IOException;

public class VoucherResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() ||
                response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if(response.getStatusCode().is5xxServerError()){
            throw new ServerErrorException("Voucher Server error",null);
        }
        if(response.getStatusCode().is4xxClientError()){
            ObjectMapper mapper = new ObjectMapper();
            VoucherError error = mapper.readValue(response.getBody(), VoucherError.class);
            throw new RuntimeException(error.error);
        }
    }

    @Getter
    @Setter
    private static class VoucherError {
        private String error;
    }
}
