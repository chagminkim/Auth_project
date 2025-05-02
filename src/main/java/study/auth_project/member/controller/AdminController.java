package study.auth_project.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.auth_project.member.dto.MemberDetailResponseDto;
import study.auth_project.member.repository.MemberRepository;
import study.auth_project.member.service.MemberService;

@RestController
public class AdminController {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    public AdminController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/api/v1/admin/{id}")
    public ResponseEntity<?> getMemberDetail(@PathVariable Long id) {

        MemberDetailResponseDto detail = memberService.getMemberDetail(id);
        return ResponseEntity.ok(detail);
    }
}
