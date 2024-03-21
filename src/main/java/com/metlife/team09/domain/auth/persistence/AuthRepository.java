package com.metlife.team09.domain.auth.persistence;

import com.metlife.team09.domain.member.persistence.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Member, Long> {
}
