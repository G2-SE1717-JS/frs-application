package com.frs.application.logic.impl;

import com.frs.application.domain.FollowAccount;
import com.frs.application.dto.FollowAccountDTO;
import com.frs.application.logic.IFollowAccountLogic;
import com.frs.application.mapper.FollowAccountMapper;
import com.frs.application.repository.FollowAccountRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowAccountLogicImpl implements IFollowAccountLogic {
    private final FollowAccountRepository repository;
    private final FollowAccountMapper mapper;
    @Override
    public List<FollowAccountDTO> getAllFollow(Long accountId) {
        List<FollowAccount> followAccountDTO = repository.findAll(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("accountId"), accountId),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        );
        return followAccountDTO.stream().map(mapper::toDto).toList();
    }

    @Override
    public FollowAccountDTO save(FollowAccountDTO followAccountDTO) {
        FollowAccount followAccount = mapper.toEntity(followAccountDTO);
        followAccount = repository.save(followAccount);
        return mapper.toDto(followAccount);
    }

    @Override
    public FollowAccountDTO getByIdAndFollowedId(Long accountId, Long followedAccountId, boolean isDeleted) {
        FollowAccount followAccount = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("accountId"), accountId),
                        criteriaBuilder.equal(root.get("followedAccountId"), followedAccountId),
                        criteriaBuilder.equal(root.get("isDeleted"), isDeleted)
                )
        ).orElse(null);
        return mapper.toDto(followAccount);
    }

    @Override
    public FollowAccountDTO getById(Long aLong) {
        return null;
    }
}
