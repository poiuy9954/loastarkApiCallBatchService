package com.laapicallbat.lostarkapicallbatchservice.biz.rest.auction.service;

import com.laapicallbat.lostarkapicallbatchservice.aa.customclass.MyRestTemplate;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.dto.LostarkAuctionItemsRequestDTO;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.dto.LostarkAuctionItemsResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RestAuctionService {

    private final MyRestTemplate restTemplate;
    public RestAuctionService(MyRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String,String> getOptions(){
        String url = "@{domainName.home.auction.options}";
        ResponseEntity<Map> auctionOpt = restTemplate.getForEntity(url, Map.class);
        return auctionOpt.getBody();
    }

    public LostarkAuctionItemsResponseDTO postItems(LostarkAuctionItemsRequestDTO lostarkAuctionItemsRequestDTO){
        String url = "@{domainName.home.auction.items}";
        ResponseEntity<LostarkAuctionItemsResponseDTO> response = restTemplate.postForEntity(url, lostarkAuctionItemsRequestDTO, LostarkAuctionItemsResponseDTO.class);
        return response.getBody();
    }

}
