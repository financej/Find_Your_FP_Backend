package com.metlife.team09.domain.feign.client;

import com.metlife.team09.domain.feign.config.KakaoAuthFeignConfig;
import com.metlife.team09.domain.feign.dto.KakaoAuthInfoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "KakaoAuthInfoClient", url = "https://kapi.kakao.com/v2/user/me", configuration = KakaoAuthFeignConfig.class)
public interface KakaoAuthInfoClient {
	@GetMapping
	KakaoAuthInfoResponseDto getInfo(@RequestHeader(name = "Authorization") String accessToken);
}
