package com.laapicallbat.lostarkapicallbatchservice.biz.auction.mapstruct;

import com.google.gson.Gson;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.dto.LostarkAuctionItemsResponseDTO;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.entity.ItemExchangeEntity;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class LostarkAuctionToEntityMapperTests {

    @Autowired
    private LostarkAuctionToEntityMapper mapper;
    @Autowired
    private Gson gson;

    @Test
    public void toEntityTest_List() {
        String json = "{\"PageNo\":1,\"PageSize\":10,\"TotalCount\":724,\"Items\":[{\"Name\":\"7레벨 멸화의 보석\",\"Grade\":\"전설\",\"Tier\":3,\"Level\":0,\"Icon\":\"https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_9_52.png\",\"GradeQuality\":0,\"AuctionInfo\":{\"StartPrice\":11111,\"BuyPrice\":12600,\"BidPrice\":0,\"EndDate\":\"2025-01-17T08:31:07.22\",\"BidCount\":0,\"BidStartPrice\":11111,\"IsCompetitive\":false,\"TradeAllowCount\":0},\"Options\":[{\"Type\":\"GEM_SKILL_DAMAGE\",\"OptionName\":\"끈적이는 이끼늪\",\"OptionNameTripod\":\"\",\"Value\":21.0,\"IsPenalty\":false,\"ClassName\":\"서머너\",\"IsValuePercentage\":false}]},{\"Name\":\"7레벨 멸화의 보석\",\"Grade\":\"전설\",\"Tier\":3,\"Level\":0,\"Icon\":\"https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_9_52.png\",\"GradeQuality\":0,\"AuctionInfo\":{\"StartPrice\":12000,\"BuyPrice\":12700,\"BidPrice\":0,\"EndDate\":\"2025-01-17T10:46:04.457\",\"BidCount\":0,\"BidStartPrice\":12000,\"IsCompetitive\":false,\"TradeAllowCount\":0},\"Options\":[{\"Type\":\"GEM_SKILL_DAMAGE\",\"OptionName\":\"다크 리저렉션\",\"OptionNameTripod\":\"\",\"Value\":21.0,\"IsPenalty\":false,\"ClassName\":\"아르카나\",\"IsValuePercentage\":false}]},{\"Name\":\"7레벨 멸화의 보석\",\"Grade\":\"전설\",\"Tier\":3,\"Level\":0,\"Icon\":\"https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_9_52.png\",\"GradeQuality\":0,\"AuctionInfo\":{\"StartPrice\":12000,\"BuyPrice\":12700,\"BidPrice\":0,\"EndDate\":\"2025-01-17T10:46:07.97\",\"BidCount\":0,\"BidStartPrice\":12000,\"IsCompetitive\":false,\"TradeAllowCount\":0},\"Options\":[{\"Type\":\"GEM_SKILL_DAMAGE\",\"OptionName\":\"셀레스티얼 레인\",\"OptionNameTripod\":\"\",\"Value\":21.0,\"IsPenalty\":false,\"ClassName\":\"아르카나\",\"IsValuePercentage\":false}]},{\"Name\":\"7레벨 멸화의 보석\",\"Grade\":\"전설\",\"Tier\":3,\"Level\":0,\"Icon\":\"https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_9_52.png\",\"GradeQuality\":0,\"AuctionInfo\":{\"StartPrice\":12000,\"BuyPrice\":12700,\"BidPrice\":0,\"EndDate\":\"2025-01-17T10:46:16.357\",\"BidCount\":0,\"BidStartPrice\":12000,\"IsCompetitive\":false,\"TradeAllowCount\":0},\"Options\":[{\"Type\":\"GEM_SKILL_DAMAGE\",\"OptionName\":\"이보크\",\"OptionNameTripod\":\"\",\"Value\":21.0,\"IsPenalty\":false,\"ClassName\":\"아르카나\",\"IsValuePercentage\":false}]},{\"Name\":\"7레벨 멸화의 보석\",\"Grade\":\"전설\",\"Tier\":3,\"Level\":0,\"Icon\":\"https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_9_52.png\",\"GradeQuality\":0,\"AuctionInfo\":{\"StartPrice\":12000,\"BuyPrice\":12700,\"BidPrice\":0,\"EndDate\":\"2025-01-17T10:46:20.2\",\"BidCount\":0,\"BidStartPrice\":12000,\"IsCompetitive\":false,\"TradeAllowCount\":0},\"Options\":[{\"Type\":\"GEM_SKILL_DAMAGE\",\"OptionName\":\"체크메이트\",\"OptionNameTripod\":\"\",\"Value\":21.0,\"IsPenalty\":false,\"ClassName\":\"아르카나\",\"IsValuePercentage\":false}]},{\"Name\":\"7레벨 멸화의 보석\",\"Grade\":\"전설\",\"Tier\":3,\"Level\":0,\"Icon\":\"https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_9_52.png\",\"GradeQuality\":0,\"AuctionInfo\":{\"StartPrice\":12400,\"BuyPrice\":12400,\"BidPrice\":0,\"EndDate\":\"2025-01-17T11:20:16.597\",\"BidCount\":0,\"BidStartPrice\":12400,\"IsCompetitive\":false,\"TradeAllowCount\":0},\"Options\":[{\"Type\":\"GEM_SKILL_DAMAGE\",\"OptionName\":\"글러트니\",\"OptionNameTripod\":\"\",\"Value\":21.0,\"IsPenalty\":false,\"ClassName\":\"소울이터\",\"IsValuePercentage\":false}]},{\"Name\":\"7레벨 멸화의 보석\",\"Grade\":\"전설\",\"Tier\":3,\"Level\":0,\"Icon\":\"https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_9_52.png\",\"GradeQuality\":0,\"AuctionInfo\":{\"StartPrice\":12444,\"BuyPrice\":12444,\"BidPrice\":0,\"EndDate\":\"2025-01-17T11:15:31.267\",\"BidCount\":0,\"BidStartPrice\":12444,\"IsCompetitive\":false,\"TradeAllowCount\":0},\"Options\":[{\"Type\":\"GEM_SKILL_DAMAGE\",\"OptionName\":\"난화격\",\"OptionNameTripod\":\"\",\"Value\":21.0,\"IsPenalty\":false,\"ClassName\":\"기공사\",\"IsValuePercentage\":false}]},{\"Name\":\"7레벨 멸화의 보석\",\"Grade\":\"전설\",\"Tier\":3,\"Level\":0,\"Icon\":\"https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_9_52.png\",\"GradeQuality\":0,\"AuctionInfo\":{\"StartPrice\":12500,\"BuyPrice\":12500,\"BidPrice\":0,\"EndDate\":\"2025-01-17T11:15:17.367\",\"BidCount\":0,\"BidStartPrice\":12500,\"IsCompetitive\":false,\"TradeAllowCount\":0},\"Options\":[{\"Type\":\"GEM_SKILL_DAMAGE\",\"OptionName\":\"흡철장\",\"OptionNameTripod\":\"\",\"Value\":21.0,\"IsPenalty\":false,\"ClassName\":\"기공사\",\"IsValuePercentage\":false}]},{\"Name\":\"7레벨 멸화의 보석\",\"Grade\":\"전설\",\"Tier\":3,\"Level\":0,\"Icon\":\"https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_9_52.png\",\"GradeQuality\":0,\"AuctionInfo\":{\"StartPrice\":12500,\"BuyPrice\":12500,\"BidPrice\":0,\"EndDate\":\"2025-01-17T11:17:13.097\",\"BidCount\":0,\"BidStartPrice\":12500,\"IsCompetitive\":false,\"TradeAllowCount\":0},\"Options\":[{\"Type\":\"GEM_SKILL_DAMAGE\",\"OptionName\":\"대쉬 어퍼 파이어\",\"OptionNameTripod\":\"\",\"Value\":21.0,\"IsPenalty\":false,\"ClassName\":\"워로드\",\"IsValuePercentage\":false}]},{\"Name\":\"7레벨 멸화의 보석\",\"Grade\":\"전설\",\"Tier\":3,\"Level\":0,\"Icon\":\"https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_9_52.png\",\"GradeQuality\":0,\"AuctionInfo\":{\"StartPrice\":12500,\"BuyPrice\":12500,\"BidPrice\":0,\"EndDate\":\"2025-01-17T11:18:13.483\",\"BidCount\":0,\"BidStartPrice\":12500,\"IsCompetitive\":false,\"TradeAllowCount\":0},\"Options\":[{\"Type\":\"GEM_SKILL_DAMAGE\",\"OptionName\":\"화조강림\",\"OptionNameTripod\":\"\",\"Value\":21.0,\"IsPenalty\":false,\"ClassName\":\"배틀마스터\",\"IsValuePercentage\":false}]}]} \n";
        LostarkAuctionItemsResponseDTO lostarkAuctionItemsResponseDTO = gson.fromJson(json, LostarkAuctionItemsResponseDTO.class);
        List<ItemExchangeEntity> entities = mapper.toEntity(lostarkAuctionItemsResponseDTO,30000);

        Assertions.assertEquals(entities.size(), lostarkAuctionItemsResponseDTO.getItems().size());
    }

    @Test
    public void toEntityTest_Empty() {
        LostarkAuctionItemsResponseDTO dto = new LostarkAuctionItemsResponseDTO();
        List<ItemExchangeEntity> entities =  mapper.toEntity(dto,30000);

        Assertions.assertTrue(entities.isEmpty());

    }
}
