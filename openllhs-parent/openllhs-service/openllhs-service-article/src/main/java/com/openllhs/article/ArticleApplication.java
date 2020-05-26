package com.openllhs.article;

import entity.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ArticleApplication {


    public static void main(String[] args){
        SpringApplication.run(ArticleApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(0,0);
    }
}
