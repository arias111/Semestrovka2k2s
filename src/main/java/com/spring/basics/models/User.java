package com.spring.basics.models;

import com.spring.basics.dto.forms.SignUpForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Cookie> cookieList;

    @Enumerated(value = EnumType.STRING)
    private State state;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum  State {
        ACTIVE,BANNED
    }
    public enum Role {
        USER, ADMIN
    }

    public boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public boolean isBanned() {
        return this.state == State.BANNED;
    }
    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }
    private String image;

    private boolean proved;

    private String currentConfirmationCode;

    public User(Long id) {
        this.id = id;
    }

    public static User fromSignUpForm(SignUpForm form) {
        return User.builder()
                .email(form.getEmail())
                .username(form.getUsername())
                .role(User.Role.USER)
                .state(User.State.ACTIVE)
                .build();
    }
}
