package study.http.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import study.http.domain.Member;


public interface MemberRepository extends JpaRepository<Member,Long> {
}
