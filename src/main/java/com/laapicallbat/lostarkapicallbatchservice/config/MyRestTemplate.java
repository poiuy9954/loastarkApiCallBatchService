package com.laapicallbat.lostarkapicallbatchservice.config;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.Set;

@Component
public class MyRestTemplate extends RestTemplate {

    private final DomainConfig domainConfig;

    public MyRestTemplate(DomainConfig domainConfig) {
        this.domainConfig = domainConfig;
    }

    @Override
    public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return super.getForObject(this.getFullUrl(url), responseType, uriVariables);
    }

    @Override
    public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return super.getForObject(this.getFullUrl(url), responseType, uriVariables);
    }


    @Override
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return super.getForEntity(this.getFullUrl(url), responseType, uriVariables);
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return super.getForEntity(this.getFullUrl(url), responseType, uriVariables);
    }

    @Override
    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return super.postForEntity(this.getFullUrl(url), request, responseType, uriVariables);
    }

    @Override
    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return super.postForEntity(this.getFullUrl(url), request, responseType, uriVariables);
    }

    @Override
    public <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return super.postForObject(this.getFullUrl(url), request, responseType, uriVariables);
    }

    @Override
    public <T> T postForObject(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return super.postForObject(this.getFullUrl(url), request, responseType, uriVariables);
    }

    @Override
    public <T> T patchForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return super.patchForObject(this.getFullUrl(url), request, responseType, uriVariables);
    }

    @Override
    public <T> T patchForObject(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return super.patchForObject(this.getFullUrl(url), request, responseType, uriVariables);
    }

    @Override
    public void put(String url, Object request, Map<String, ?> uriVariables) throws RestClientException {
        super.put(this.getFullUrl(url), request, uriVariables);
    }

    @Override
    public void put(String url, Object request, Object... uriVariables) throws RestClientException {
        super.put(this.getFullUrl(url), request, uriVariables);
    }

    @Override
    public void delete(String url, Map<String, ?> uriVariables) throws RestClientException {
        super.delete(this.getFullUrl(url), uriVariables);
    }

    @Override
    public void delete(String url, Object... uriVariables) throws RestClientException {
        super.delete(this.getFullUrl(url), uriVariables);
    }

    @Override
    public Set<HttpMethod> optionsForAllow(String url, Map<String, ?> uriVariables) throws RestClientException {
        return super.optionsForAllow(this.getFullUrl(url), uriVariables);
    }

    @Override
    public Set<HttpMethod> optionsForAllow(String url, Object... uriVariables) throws RestClientException {
        return super.optionsForAllow(this.getFullUrl(url), uriVariables);
    }

    private String getFullUrl(String url) throws RuntimeException {
        String fullUrl;
        try {
            fullUrl= domainConfig.replaceUri(url);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fullUrl;
    }
}
