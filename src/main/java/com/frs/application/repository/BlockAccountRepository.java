package com.frs.application.repository;

import com.frs.application.domain.BlockAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockAccountRepository extends JpaRepository<BlockAccount, Long>, JpaSpecificationExecutor<BlockAccount> {
}
