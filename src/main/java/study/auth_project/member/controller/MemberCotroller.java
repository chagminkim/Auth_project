package study.auth_project.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.auth_project.member.dto.MemberLoginRequest;
import study.auth_project.member.dto.MemberRequestDto;
import study.auth_project.member.dto.MemberResponseDto;
import study.auth_project.member.repository.MemberRepository;
import study.auth_project.member.service.MemberService;

import java.util.List;

@RestController
public class MemberCotroller {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    MemberCotroller(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/api/v1/create")
    public ResponseEntity<?> createMember(@RequestBody MemberRequestDto memberRequestDto) {
        try {
            return ResponseEntity.ok(memberService.createMember(memberRequestDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //로그인
    @PostMapping("/api/v1/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginRequest request) {
        try {
            return ResponseEntity.ok(memberService.login(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //회원 정보 단체 조회
    @GetMapping("/api/v1/getusers")
    public ResponseEntity<List<MemberResponseDto>> getMembers() {
        List<MemberResponseDto> responseDtos = memberService.getMembers();
        return ResponseEntity.ok(responseDtos);
    }

    //회원 정보 단건 조회
    @GetMapping("/api/v1/getuser/{id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable("id") Long id) {

        MemberResponseDto dto = memberService.getMember(id);
        return ResponseEntity.ok(dto);
    }

    //회원 정보 탈퇴
    @DeleteMapping("/api/v1/delete/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("삭제 되었습니다.");
    }
}
