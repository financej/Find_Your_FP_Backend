package com.metlife.team09.domain.member.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metlife.team09.domain.member.application.MemberService;
import com.metlife.team09.domain.member.application.dto.UserAddressRequestDto;
import com.metlife.team09.domain.member.application.dto.UserInfoResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/info")
	public ResponseEntity<UserInfoResponseDto> getUserInfo() {
		final String name = SecurityContextHolder.getContext().getAuthentication().getName();
		final UserInfoResponseDto userInfo = memberService.getUserInfo(Long.parseLong(name));

		return ResponseEntity.ok(userInfo);
	}

	@PutMapping("/info")
	public ResponseEntity<?> addUserAddress(@RequestBody final UserAddressRequestDto request){
		final String name = SecurityContextHolder.getContext().getAuthentication().getName();

		memberService.addUserAddress(Long.parseLong(name), request.address());

		return ResponseEntity.ok().build();
	}
}
