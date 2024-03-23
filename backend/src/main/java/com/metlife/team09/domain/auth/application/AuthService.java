package com.metlife.team09.domain.auth.application;

import java.util.HashMap;
import java.util.Map;

import com.metlife.team09.domain.member.exception.MemberNotFound;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metlife.team09.domain.auth.application.dto.LoginKakaoRequestDto;
import com.metlife.team09.domain.auth.application.dto.LoginRequestDto;
import com.metlife.team09.domain.auth.application.dto.LoginResponseDto;
import com.metlife.team09.domain.auth.application.dto.SignupRequestDto;
import com.metlife.team09.domain.auth.application.dto.SignupResponseDto;
import com.metlife.team09.domain.auth.application.dto.TokenResponseDto;
import com.metlife.team09.domain.member.persistence.Member;
import com.metlife.team09.domain.member.persistence.MemberRepository;
import com.metlife.team09.global.jwt.JwtTokenProvider;
import com.metlife.team09.infra.feign.client.KakaoAuthInfoClient;
import com.metlife.team09.infra.feign.client.KakaoAuthLoginClient;
import com.metlife.team09.infra.feign.dto.KakaoAuthInfoResponseDto;
import com.metlife.team09.infra.feign.dto.KakaoAuthLoginResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoAuthInfoClient kakaoAuthInfoClient;
    private final KakaoAuthLoginClient kakaoAuthLoginClient;

    @Value("${kakao.client-id}")
    private String KAKAO_CLIENT_ID;
    @Value("${kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET;

    @Transactional
    public TokenResponseDto loginKakao(final LoginKakaoRequestDto request) {
        final KakaoAuthLoginResponseDto kakaoLoginAccessToken = getKakaoLoginAccessToken(request);
        final KakaoAuthInfoResponseDto kakaoAuthInfoResponse = getKakaoAuthInfoResponse(kakaoLoginAccessToken);

        final String email = kakaoAuthInfoResponse.kakaoAccount().getEmail();
        final boolean isMemberExists = memberRepository.findByEmail(email).isPresent();

        Member member;
        // 로그인
        if(isMemberExists) {
            member = memberRepository.findByEmail(email)
                    .orElseThrow(MemberNotFound::new);
        }
        // 회원가입
        else {
            member = Member.builder()
                    .email(email)
                    .build();
            member = memberRepository.save(member);
        }

        final String accessToken = jwtTokenProvider.createToken(member.getId().toString());

        return new TokenResponseDto(accessToken,member.getEmail(),member.getIsAdmin());
    }

    private KakaoAuthInfoResponseDto getKakaoAuthInfoResponse(final KakaoAuthLoginResponseDto kakaoLoginAccessToken) {
        return kakaoAuthInfoClient.getInfo("Bearer " + kakaoLoginAccessToken.accessToken(), "application/x-www-form-urlencoded;charset=utf-8");
    }

    private KakaoAuthLoginResponseDto getKakaoLoginAccessToken(final LoginKakaoRequestDto request) {
        final Map<String, Object> parmaMap = new HashMap<>();

        parmaMap.put("grant_type", "authorization_code");
        parmaMap.put("client_id", KAKAO_CLIENT_ID);
        parmaMap.put("client_secret", KAKAO_CLIENT_SECRET);
        parmaMap.put("redirect_uri", request.redirectUri()); // 카카오로그인 요청한 프론트 측 주소
        parmaMap.put("code", request.code());

        return kakaoAuthLoginClient.getAccessToken(parmaMap);
    }
}
