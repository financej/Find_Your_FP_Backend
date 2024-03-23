package com.metlife.team09.domain.member.application;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metlife.team09.domain.member.application.dto.UserInfoResponseDto;
import com.metlife.team09.domain.member.persistence.Member;
import com.metlife.team09.domain.member.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	public UserInfoResponseDto getUserInfo(long userId){
		Member member = memberRepository.findById(userId)
			.orElseThrow(() -> new UsernameNotFoundException("유저가 존재하지 않습니다."));
		return new UserInfoResponseDto(member.getId() ,member.getEmail());
	}
	@Transactional
	public void addUserAddress(long userId,String address){
		Member member = memberRepository.findById(userId)
			.orElseThrow(() -> new UsernameNotFoundException("유저가 존재하지 않습니다."));
		member.setAddress(address);
	}

}
