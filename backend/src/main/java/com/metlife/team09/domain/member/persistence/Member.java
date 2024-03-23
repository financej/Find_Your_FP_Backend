package com.metlife.team09.domain.member.persistence;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    @Embedded
    private Address address;
    public boolean isAdmin;

    @Builder
    public Member(final String email)  {
        this.email = email;
    }

    public void setAddress(Address address){
        this.address = address;
    }
}
