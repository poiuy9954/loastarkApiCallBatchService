package com.laapicallbat.lostarkapicallbatchservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "api-domain")
public class DomainConfig {

    private Map<String,Map<String,String>> url;
    private final static String PATTERN ="@\\{[^}]*\\}";

    public String replaceUri(String aliasUri) throws Exception {
        Matcher matcher = Pattern.compile(PATTERN).matcher(aliasUri);
        if(matcher.find()){
            String uriAlias = matcher.group();
            String[] splitAlias = uriAlias.replace("@{","").replace("}","").split("\\.");
            return this.getFullUri(splitAlias);
        }
        else throw new Exception("Alias-Uri를 확인하세요");
    }

    private String getFullUri(String[] alias) throws Exception {
        if(alias.length<4) throw new Exception("도메인리소스를 확인하세요");
        return this.url.get(alias[0]).get(alias[1])+this.url.get(alias[2]).get(alias[3]);
    }

    @Override
    public String toString() {
        return "DomainConfig{" +
                "uri=" + url +
                '}';
    }
}
