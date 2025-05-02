package study.auth_project.member.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import study.auth_project.member.entity.Member;

import java.time.LocalDateTime;

public class MemberResponseDto {


    private Long id;

    private String name;

    private LocalDateTime created_at;

    private String email;

    @Enumerated(EnumType.STRING)
    private Member.Status status;

    public enum Status {
        ACTIVE, INACTIVE
    }

    public MemberResponseDto(Long id, String name, Member.Status status, LocalDateTime created_at, String email) {
        this.id = id;

        this.name = name;

        this.status = status;

        this.created_at = created_at;

        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Member.Status getStatus() {
        return status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public String getEmail() {
        return email;
    }
}
