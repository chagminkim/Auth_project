package study.auth_project.member.dto;

public record MemberSignupRequest(
        String email,
        String password
) {
}
