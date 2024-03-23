package com.metlife.team09.domain.member.persistence;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Embeddable
@AllArgsConstructor
public class Address {
	String siDo;
	String siGunGu;
	String dong;

	public Address() {
	}
}
