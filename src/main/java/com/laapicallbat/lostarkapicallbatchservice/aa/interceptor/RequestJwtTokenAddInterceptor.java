package com.laapicallbat.lostarkapicallbatchservice.aa.interceptor;

import com.laapicallbat.lostarkapicallbatchservice.aa.config.JwtConfig;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestJwtTokenAddInterceptor implements ClientHttpRequestInterceptor {

    private final JwtConfig jwtConfig;

    public RequestJwtTokenAddInterceptor(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("Authorization", "Bearer " + jwtConfig.getToken());
        request.getHeaders().add("Content-Type", "application/json");
        request.getHeaders().add("Accept", "application/json");
        return execution.execute(request, body);
    }
}
