package com.laapicallbat.lostarkapicallbatchservice;

import com.laapicallbat.lostarkapicallbatchservice.aa.config.DomainConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class RequestUriReplaceInterceptorTests {

    @Autowired
    private DomainConfig domainConfig;

    @Test
    public void test(){

        Pattern pattern = Pattern.compile("@\\{[^}]*\\}");
        String uri = "@{domainName.home.market.options}";
//        String uri = "@{domainName.home.market.options}?page=3";
        Matcher matcher = pattern.matcher(uri);


        if(matcher.find()) {

            String uriAlias = matcher.group();
            String[] splitAlias = uriAlias.replace("@{","").replace("}","").split("\\.");
            System.out.println(Arrays.toString(splitAlias));
            System.out.println(domainConfig.getUrl());
            String main = domainConfig.getUrl().get(splitAlias[0]).get(splitAlias[1]);
            String sub = domainConfig.getUrl().get(splitAlias[2]).get(splitAlias[3]);
            String fullUri = main+sub;
            System.out.println(fullUri);
            uri = uri.replace(uriAlias,fullUri);
            System.out.printf("result : {%s}",uri );


        }else {
            System.out.println("Asda???");
        }
    }

}
