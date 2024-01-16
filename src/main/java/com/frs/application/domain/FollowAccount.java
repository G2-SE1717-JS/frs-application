package com.frs.application.domain;

import com.frs.core.base.BaseDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "followAccount")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class FollowAccount extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountID;
    private Long followedAccountID;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
