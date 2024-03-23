package com.metlife.team09.domain.member.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.metlife.team09.domain.member.application.dto.PlannerListRequestDto;
import com.metlife.team09.domain.member.application.dto.PlannerListResponseDto;
import com.metlife.team09.domain.member.persistence.Member;
import com.metlife.team09.domain.member.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlannerService {

	private final MemberRepository memberRepository;

	public List<PlannerListResponseDto> getPlanners(String name) {
		Member member1 = memberRepository.findById(Long.parseLong(name)).get();
		List<Member> allByAddress_dong = memberRepository.findAllByAddress_DongAndIsAdminTrue(member1.getAddress().getDong());
		return allByAddress_dong.stream()
			.map(member -> new PlannerListResponseDto(
				member.getId(),
				member.getEmail(),
				member.getAddress() != null ? member.getAddress().getSiDo()+" "+member.getAddress().getSiGunGu()+" "+member.getAddress().getDong() : null
			))
			.collect(Collectors.toList());
	}

}
