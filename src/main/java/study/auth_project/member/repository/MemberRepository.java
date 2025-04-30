package study.auth_project.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.auth_project.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
}
