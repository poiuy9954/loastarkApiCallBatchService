package com.laapicallbat.lostarkapicallbatchservice.config;

import com.laapicallbat.lostarkapicallbatchservice.aa.config.DomainConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@SpringBootTest
public class DomainConfigTests {

    @Autowired
    private DomainConfig domainConfig;

    @Test
    public void domainConfig_getMain(){
        Assertions.assertEquals("https://developer-lostark.game.onstove.com", domainConfig.getUrl().get("domainName").get("home"));
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

    @Test
    public void domainConfig_replaceUri_market_options() throws Exception {
        String testAliasUri = "@{domainName.home.market.options}";
        String fulUri = domainConfig.replaceUri(testAliasUri);
        Assertions.assertEquals("https://developer-lostark.game.onstove.com/markets/options",fulUri);
    }

    @Test
    public void domainConfig_replaceUri_auction_items() throws Exception {
        String testAliasUri = "@{domainName.home.auction.items}";
        String fulUri = domainConfig.replaceUri(testAliasUri);
        Assertions.assertEquals("https://developer-lostark.game.onstove.com/auctions/items",fulUri);
    }

    @Test
    public void domainConfig_replaceUri_matchError() throws Exception {
        Assertions.assertThrows(Exception.class,
                ()->{
                        String testAliasUri = "@{domainName.home.auction.items";
                        domainConfig.replaceUri(testAliasUri);}
        );
    }

    @Test
    public void domainConfig_replaceUri_AliasDomainError_cnt() throws Exception {
        Assertions.assertThrows(Exception.class,
                ()->{
                    String testAliasUri = "@{domainName.home.auction}";
                    domainConfig.replaceUri(testAliasUri);
                });
    }

    @Test
    public void test(){

        String aliasUri4 = "@{domainName.home.market.options}";

        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("asdasd.com")
                .path(aliasUri4)
                .build().toUri();

        System.out.println(uri);
    }


}
