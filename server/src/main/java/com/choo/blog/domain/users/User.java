package com.choo.blog.domain.users;

import com.choo.blog.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "users")
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String name;
    private String password;
    private String nickname;
    private String profileImage;
    private LocalDate birthdate;
    private String profileMessage;
    private String introduction;
    private String phone;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void encrypte(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
