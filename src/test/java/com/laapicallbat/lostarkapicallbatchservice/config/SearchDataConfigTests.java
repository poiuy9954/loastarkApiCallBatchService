package com.laapicallbat.lostarkapicallbatchservice.config;

import com.laapicallbat.lostarkapicallbatchservice.aa.config.SearchDataConfig;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class SearchDataConfigTests {

    @Autowired
    private SearchDataConfig searchDataConfig;

    @Test
    public void searchDataConfigTest() {
        List<String> gemList = new ArrayList<>();

        gemList.add("9레벨 멸화의 보석");
        gemList.add("10레벨 멸화의 보석");
        gemList.add("9레벨 홍염의 보석");
        gemList.add("10레벨 홍염의 보석");
        gemList.add("7레벨 작열의 보석");
        gemList.add("8레벨 작열의 보석");
        gemList.add("9레벨 작열의 보석");
        gemList.add("10레벨 작열의 보석");
        gemList.add("7레벨 겁화의 보석");
        gemList.add("8레벨 겁화의 보석");
        gemList.add("9레벨 겁화의 보석");
        gemList.add("10레벨 겁화의 보석");

        Assertions.assertTrue(searchDataConfig.getGem().containsAll(gemList));
    }
}
