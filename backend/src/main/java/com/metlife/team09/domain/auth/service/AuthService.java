package com.metlife.team09.domain.auth.service;

import com.metlife.team09.domain.auth.application.dto.LoginRequestDto;
import com.metlife.team09.domain.auth.application.dto.LoginResponseDto;
import com.metlife.team09.domain.auth.application.dto.SignupRequestDto;
import com.metlife.team09.domain.auth.application.dto.SignupResponseDto;
import com.metlife.team09.domain.member.persistence.Member;
import com.metlife.team09.domain.member.persistence.MemberRepository;
import com.metlife.team09.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public SignupResponseDto signup(final SignupRequestDto requestDto) {

        final Member member = Member.builder()
                .email(requestDto.email())
                .password(requestDto.password())
                .build();

        memberRepository.save(member);

        return new SignupResponseDto(member.getId());
    }

    public LoginResponseDto login(final LoginRequestDto requestDto) {

        final String token = jwtTokenProvider.createToken(requestDto.email());

        return new LoginResponseDto(token);
    }
}
