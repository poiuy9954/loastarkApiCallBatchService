package com.laapicallbat.lostarkapicallbatchservice.aa.interceptor;


import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Log4j2
@Component
public class MyRestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.debug("Request Method : Uri: {}:{}" , request.getMethod(),request.getURI());
        log.debug("Request Headers: {}" , request.getHeaders());
        log.debug("Request Body: {}" , new String(body, StandardCharsets.UTF_8));

        ClientHttpResponse response = execution.execute(request, body);

        ClientHttpResponseWrapper responseWrapper = new ClientHttpResponseWrapper(response);
        String responseBody = new String(responseWrapper.getResBody(), StandardCharsets.UTF_8);
        log.debug("Response Status Code: {}" , response.getStatusCode());
        log.debug("Response Headers: {}" , response.getHeaders());
        log.debug("Response Body: {}" , responseBody);

        return responseWrapper;
    }

    private static class ClientHttpResponseWrapper implements ClientHttpResponse {
        private final ClientHttpResponse response;

        @Getter
        private final byte[] resBody;

        public ClientHttpResponseWrapper(ClientHttpResponse response) throws IOException {
            this.response = response;
            this.resBody = response.getBody().readAllBytes();
        }

        @Override
        public HttpStatusCode getStatusCode() throws IOException {
            return response.getStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return response.getStatusText();
        }

        @Override
        public void close() {
            response.close();
        }

        @Override
        public InputStream getBody() throws IOException {
            return new ByteArrayInputStream(resBody);
        }

        @Override
        public HttpHeaders getHeaders() {
            return response.getHeaders();
        }
    }
}
