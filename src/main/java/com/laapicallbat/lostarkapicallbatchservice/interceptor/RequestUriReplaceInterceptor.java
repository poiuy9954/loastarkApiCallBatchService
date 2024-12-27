package com.laapicallbat.lostarkapicallbatchservice.interceptor;

import com.laapicallbat.lostarkapicallbatchservice.config.DomainConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aopalliance.intercept.Interceptor;
import org.springframework.asm.Type;
import org.springframework.cglib.transform.impl.InterceptFieldFilter;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Component
public class RequestUriReplaceInterceptor implements ClientHttpRequestInterceptor {

    private final DomainConfig domainConfig;
    RequestUriReplaceInterceptor(DomainConfig domainConfig){
        this.domainConfig = domainConfig;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        return null;
    }
}
