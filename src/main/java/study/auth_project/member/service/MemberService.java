package study.auth_project.member.service;


import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import study.auth_project.member.dto.MemberDetailResponseDto;
import study.auth_project.member.dto.MemberLoginRequest;
import study.auth_project.member.dto.MemberRequestDto;
import study.auth_project.member.dto.MemberResponseDto;
import study.auth_project.member.entity.Member;
import study.auth_project.member.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public MemberResponseDto createMember(MemberRequestDto request) {

        if (request.getName().length() < 4) {
            throw new IllegalArgumentException("이름은 4글자 이상이여야 합니다.");
        }

        if (request.getPassword().length() < 6) {
            throw new IllegalArgumentException("비민번호는 6글자 이상이여합니다.");
        }

        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다");
        }
        //비밀번호 암호화 코드
        String encodedPwd = passwordEncoder.encode(request.getPassword());

        Member entity = new Member(request.getEmail(), request.getName(), encodedPwd, LocalDateTime.now(), Member.Status.ACTIVE);

        Member member = memberRepository.save(entity);

        MemberResponseDto memberResponseDto = new MemberResponseDto(member.getId(), member.getName(), member.getStatus(), member.getCreated_at(), member.getEmail());

        return memberResponseDto;
    }

    public boolean login(MemberLoginRequest request) {
        if (!memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("사용자가 존재하지않습니다.");
        }

        Member member = memberRepository.findByEmail(request.getEmail()).get();

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            return false;
        }
        return true;
    }

    public List<MemberResponseDto> getMembers() {

        List<Member> actionMembers = memberRepository.findAllByStatus(Member.Status.ACTIVE);

        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();

        for (Member member : actionMembers) {
            memberResponseDtos.add(new MemberResponseDto(member.getId(), member.getName(), member.getStatus(), member.getCreated_at(), member.getEmail()));
        }
        return memberResponseDtos;
    }

    public MemberResponseDto getMember(Long id) {
        Member member = memberRepository.findById(id).get();

        return new MemberResponseDto(member.getId(), member.getName(), member.getStatus(), member.getCreated_at(), member.getEmail());
    }

    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        Member member = memberRepository.findById(id).get();

        if (member.getStatus() == Member.Status.INACTIVE) {
            throw new IllegalArgumentException("이미 탈퇴한 사용자입니다");
        }

        member.setStatus(Member.Status.INACTIVE);
        member.setCreated_at(LocalDateTime.now());
    }

    public MemberDetailResponseDto getMemberDetail(Long id) {

        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("존재하지 않은 사용자입니다.");
        }

        Member member = memberRepository.findById(id).get();

        return new MemberDetailResponseDto(member.getId(), member.getName(), member.getStatus(), member.getPassword(), member.getEmail());
    }

}
