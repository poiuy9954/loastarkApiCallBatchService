package com.laapicallbat.lostarkapicallbatchservice.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class DomainConfigTests {

    @Autowired
    private DomainConfig domainConfig;

    @Test
    public void test(){
        domainConfig.getUrl().forEach((k,v)-> System.out.println(k+":"+v));
    }

    @Test
    public void domainConfig_getMain(){
        Assertions.assertEquals("https://developer-lostark.game.onstove.com", domainConfig.getUrl().get("main").get("main-url"));
    }

    @Test
    public void domainConfig_getMarket(){
        Assertions.assertEquals("/markets/items",domainConfig.getUrl().get("market").get("items"));
        Assertions.assertEquals("/markets/options",domainConfig.getUrl().get("market").get("options"));
    }

    @Test
    public void domainConfig_getAuction(){
        Assertions.assertEquals("/auctions/items",domainConfig.getUrl().get("auction").get("items"));
        Assertions.assertEquals("/auctions/options",domainConfig.getUrl().get("auction").get("options"));
    }
}
