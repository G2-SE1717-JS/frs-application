package com.frs.application.domain;

import com.frs.core.base.BaseDomain;
import com.frs.core.constants.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "report")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Report extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private Long recipeId;

    private String description;

    private String reportStatus;

    private String adminResponse;

    private LocalDateTime adminResponseDate;

}
