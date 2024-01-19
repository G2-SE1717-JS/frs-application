package com.frs.application.domain;

import com.frs.core.base.BaseDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "follow_account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class FollowAccount extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private Long followedAccountId;
}
