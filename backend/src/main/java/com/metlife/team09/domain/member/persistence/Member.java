package com.metlife.team09.domain.member.persistence;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "is_admin")
    public Boolean isAdmin;

    @Builder
    public Member(final String email)  {
        this.email = email;
    }

    public void setAddress(final Address address){
        this.address = address;
    }
}
