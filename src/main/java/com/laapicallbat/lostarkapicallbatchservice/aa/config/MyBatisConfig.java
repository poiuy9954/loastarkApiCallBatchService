package com.laapicallbat.lostarkapicallbatchservice.aa.config;

import com.laapicallbat.lostarkapicallbatchservice.aa.interceptor.AddAuditingInterceptor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.laapicallbat.lostarkapicallbatchservice.biz.auction.mapper")
@Log4j2
public class MyBatisConfig {

    @Bean
    public AddAuditingInterceptor addAuditingInterceptor() {
        return new AddAuditingInterceptor();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, AddAuditingInterceptor addAuditingInterceptor) throws Exception {

        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPlugins(addAuditingInterceptor);

        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml")
        );

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);

        sessionFactory.setConfiguration(configuration);

        return sessionFactory.getObject();

    }

}
