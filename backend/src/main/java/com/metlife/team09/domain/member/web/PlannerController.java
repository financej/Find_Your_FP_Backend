package com.metlife.team09.domain.member.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metlife.team09.domain.member.application.MemberService;
import com.metlife.team09.domain.member.application.PlannerService;
import com.metlife.team09.domain.member.application.dto.PlannerListRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/planners")
public class PlannerController {
	private final PlannerService plannerService;

	@GetMapping("")
	public ResponseEntity<?> getPlanners() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return ResponseEntity.ok(plannerService.getPlanners(name));
	}
}
