package com.frs.application.logic.impl;

import com.frs.application.constants.SystemConstant;
import com.frs.application.domain.Token;
import com.frs.application.dto.TokenDTO;
import com.frs.application.logic.ITokenLogic;
import com.frs.application.mapper.TokenMapper;
import com.frs.application.repository.TokenRepository;
import com.frs.core.utils.VersionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenLogicImpl implements ITokenLogic {
    private final TokenRepository repository;
    private final TokenMapper mapper;

    @Override
    public TokenDTO findByToken(String token) {
        Token tokenEntity = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get(SystemConstant.Entity.REFRESH_TOKEN), token),
                        criteriaBuilder.equal(root.get(SystemConstant.Entity.IS_DELETED), false)
                )).orElse(null);

        return mapper.toDto(tokenEntity);
    }

    @Override
    public TokenDTO save(TokenDTO tokenDTO) {
        Token token = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get(SystemConstant.Entity.USERNAME), tokenDTO.getUsername())
        ).orElse(null);
        if (token != null) {
            token.setRefreshToken(tokenDTO.getRefreshToken());
            token.setExpiresAt(tokenDTO.getExpiresAt());
            token.setDeleted(false);
            token.setLastModifiedDate(tokenDTO.getLastModifiedDate());
        } else {
            token = mapper.toEntity(tokenDTO);
        }
        token = repository.save(token);
        return mapper.toDto(token);
    }

    @Override
    public TokenDTO getById(Long id) {
        return null;
    }
}
