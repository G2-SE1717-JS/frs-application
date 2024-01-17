package com.frs.application.domain;

import com.frs.core.base.BaseDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tool")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Tool extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
}
