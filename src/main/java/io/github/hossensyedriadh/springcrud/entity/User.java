package io.github.hossensyedriadh.springcrud.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utility.AuthorityEnum;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public final class User {
    @Id
    @Column(name = "username", updatable = false, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    private AuthorityEnum authority;

    @Column(name = "is_enabled", nullable = false)
    private boolean enabled;
}
