package com.metlife.team09.domain.auth.application;

import com.metlife.team09.domain.auth.application.dto.*;
import com.metlife.team09.domain.feign.client.KakaoAuthInfoClient;
import com.metlife.team09.domain.feign.client.KakaoAuthLoginClient;
import com.metlife.team09.domain.feign.dto.KakaoAuthInfoResponseDto;
import com.metlife.team09.domain.feign.dto.KakaoAuthLoginResponseDto;
import com.metlife.team09.domain.member.persistence.Member;
import com.metlife.team09.domain.member.persistence.MemberRepository;
import com.metlife.team09.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoAuthInfoClient kakaoAuthInfoClient;
    private final KakaoAuthLoginClient kakaoAuthLoginClient;

    @Value("${kakao.client-id}")
    private String KAKAO_CLIENT_ID;
    @Value("${kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET;


    public TokenResponseDto loginKakao(LoginKakaoRequestDto request) {
        KakaoAuthLoginResponseDto kakaoLoginAccessToken = getKakaoLoginAccessToken(request);
        KakaoAuthInfoResponseDto kakaoAuthInfoResponse = getKakaoAuthInfoResponse(kakaoLoginAccessToken);

        String email = kakaoAuthInfoResponse.kakaoAccount().getEmail();
        boolean isMemberExists = memberRepository.findByEmail(email).isPresent();

        Member member;
        // 로그인
        if(isMemberExists) {
            member = memberRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        }
        // 회원가입
        else {
            member = Member.builder()
                    .email(email)
                    .build();
            member = memberRepository.save(member);
        }

        String accessToken = jwtTokenProvider.createToken(member.getId().toString());

        return new TokenResponseDto(accessToken,member.getEmail(),member.isAdmin());
    }

    private KakaoAuthInfoResponseDto getKakaoAuthInfoResponse(KakaoAuthLoginResponseDto kakaoLoginAccessToken) {
        return kakaoAuthInfoClient.getInfo("Bearer " + kakaoLoginAccessToken.accessToken(), "application/x-www-form-urlencoded;charset=utf-8");
    }

    private KakaoAuthLoginResponseDto getKakaoLoginAccessToken(LoginKakaoRequestDto request) {
        Map<String, Object> parmaMap = new HashMap<>();
        parmaMap.put("grant_type", "authorization_code");
        parmaMap.put("client_id", KAKAO_CLIENT_ID);
        parmaMap.put("client_secret", KAKAO_CLIENT_SECRET);
        parmaMap.put("redirect_uri", request.redirectUri()); // 카카오로그인 요청한 프론트 측 주소
        parmaMap.put("code", request.code());

        return kakaoAuthLoginClient.getAccessToken(parmaMap);
    }
}
