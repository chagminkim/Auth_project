package study.auth_project.member.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import study.auth_project.member.entity.Member;

public class MemberDetailResponseDto {

    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Member.Status status;

    public enum Status {
        ACTIVE, INACTIVE
    }

    private String password;

    private String email;

    public MemberDetailResponseDto(Long id, String name, Member.Status status, String password, String email) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Member.Status getStatus() {
        return status;
    }
}
