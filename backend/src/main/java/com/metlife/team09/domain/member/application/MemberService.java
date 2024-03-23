package com.metlife.team09.domain.member.application;

import java.util.StringTokenizer;

import com.metlife.team09.domain.member.exception.MemberNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metlife.team09.domain.member.application.dto.UserInfoResponseDto;
import com.metlife.team09.domain.member.persistence.Address;
import com.metlife.team09.domain.member.persistence.Member;
import com.metlife.team09.domain.member.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	@Transactional
	public UserInfoResponseDto getUserInfo(final long userId) {
		final Member member = memberRepository.findById(userId)
			.orElseThrow(MemberNotFound::new);

		return new UserInfoResponseDto(member.getId(), member.getEmail());
	}

	@Transactional
	public void addUserAddress(final long userId, final String address) {
		final Member member = memberRepository.findById(userId)
			.orElseThrow(MemberNotFound::new);
		final StringTokenizer st = new StringTokenizer(address);

		member.setAddress(new Address(st.nextToken(), st.nextToken(), st.nextToken()));
	}

}
