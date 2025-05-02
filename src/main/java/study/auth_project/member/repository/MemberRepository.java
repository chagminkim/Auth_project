package study.auth_project.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.auth_project.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Member> findAllByStatus(Member.Status status);
}
