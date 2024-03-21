package com.metlife.team09.domain.feign.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class KakaoAuthFeignConfig {
	@Bean
	public RequestInterceptor requestInterceptor() {
		return template -> template.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	}
}
