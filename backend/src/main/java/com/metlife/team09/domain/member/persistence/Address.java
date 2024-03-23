package com.metlife.team09.domain.member.persistence;

import javax.persistence.Embeddable;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Embeddable
@AllArgsConstructor
@Getter
public class Address {
	String siDo;
	String siGunGu;
	String dong;

	public Address() {

	}
}
