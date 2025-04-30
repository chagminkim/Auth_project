package study.auth_project.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.auth_project.member.dto.MemberSignupRequest;
import study.auth_project.member.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody MemberSignupRequest request) {
        try {
            return ResponseEntity.ok(memberService.register(request.email(), request.password()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 회원 프로필 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberProfile(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(memberService.getMemberProfile(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
