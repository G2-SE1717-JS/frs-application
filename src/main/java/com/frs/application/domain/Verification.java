package com.frs.application.domain;

import com.frs.core.base.BaseDomain;
import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "verification")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Verification extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String token;
    private String email;
    private LocalDateTime expiredTime;
}
