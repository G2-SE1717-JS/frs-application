package com.frs.application.domain;

import com.frs.core.base.BaseDomain;
import com.frs.core.constants.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Account extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String username;

    private String password;

    private boolean status;

    private boolean verified;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
