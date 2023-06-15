package io.github.hossensyedriadh.springcrud.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utility.AuthorityEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public final class User {
    @Id
    @Pattern(regexp = "^[a-zA-Z0-9_]+$")
    @Column(name = "username", updatable = false, nullable = false)
    private String username;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    private AuthorityEnum authority;

    @NotNull
    @Column(name = "is_enabled", nullable = false)
    private boolean enabled;

    @PrePersist
    private void init() {
        this.enabled = true;
    }
}
