package study.auth_project.member.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@SQLDelete(sql = "UPDATE member SET status = 'INACTIVE' WHERE id = ?")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String password;

    private LocalDateTime created_at;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        ACTIVE, INACTIVE
    }

    public Member() {

    }

    public Member(String email, String name, String password, LocalDateTime created_at, Status status) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.created_at = created_at;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public Status getStatus() {
        return status;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
