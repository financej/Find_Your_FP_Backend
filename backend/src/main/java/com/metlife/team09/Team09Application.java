package com.metlife.team09;

import com.metlife.team09.infra.feign.client.KakaoAuthInfoClient;
import com.metlife.team09.infra.feign.client.KakaoAuthLoginClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {KakaoAuthInfoClient.class, KakaoAuthLoginClient.class})
public class Team09Application {

    public static void main(String[] args) {
        SpringApplication.run(Team09Application.class, args);
    }

}
