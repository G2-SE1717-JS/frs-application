package com.frs.application.logic.impl;

import com.frs.application.domain.BlockAccount;
import com.frs.application.dto.BlockAccountDTO;
import com.frs.application.logic.IBlockAccountLogic;
import com.frs.application.mapper.BlockAccountMapper;
import com.frs.application.repository.BlockAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockAccountLogicImpl implements IBlockAccountLogic {
    private final BlockAccountRepository repository;
    private final BlockAccountMapper mapper;

    @Override
    public List<BlockAccountDTO> findByAccountId(Long accountId) {
        List<BlockAccount> blockAccountDTOS = repository.findAll(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("accountId"), accountId),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
        );
        return blockAccountDTOS.stream().map(mapper::toDto).toList();
    }

    @Override
    public boolean isAccountBlocked(Long blockAccountId, Long accountId){
        BlockAccount blockAccount = repository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("blockAccountId"), blockAccountId),
                        criteriaBuilder.equal(root.get("accountId"), accountId),
                        criteriaBuilder.equal(root.get("isDeleted"), false)
                )
                ).orElse(null);
        return blockAccount != null;
    }

    @Override
    public BlockAccountDTO save(BlockAccountDTO blockAccountDTO) {
        BlockAccount blockAccount = mapper.toEntity(blockAccountDTO);
        blockAccount = repository.save(blockAccount);
        return mapper.toDto(blockAccount);
    }

    @Override
    public BlockAccountDTO getById(Long id) {
        BlockAccount blockAccount = repository.findById(id).orElse(null);
        return mapper.toDto(blockAccount);
    }
}
