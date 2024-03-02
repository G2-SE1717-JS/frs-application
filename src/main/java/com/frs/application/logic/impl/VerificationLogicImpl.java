package com.frs.application.logic.impl;

import com.frs.application.domain.Verification;
import com.frs.application.dto.VerificationDTO;
import com.frs.application.logic.VerificationLogic;
import com.frs.application.mapper.VerificationMapper;
import com.frs.application.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationLogicImpl implements VerificationLogic {
private final VerificationMapper mapper;
private final VerificationRepository repository;

    @Override
    public VerificationDTO findByEmail(String email) {
        Verification verification = repository.findOne(
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email))
                .orElse(null);
        return mapper.toDto(verification);
    }

    @Override
    public VerificationDTO save(VerificationDTO verificationDTO) {
        Verification verification = mapper.toEntity(verificationDTO);
        verification = repository.save(verification);
        return mapper.toDto(verification);
    }

    @Override
    public VerificationDTO getById(Long aLong) {
        Verification verification = repository.findById(aLong).orElse(null);
        return mapper.toDto(verification);
    }
}
