package com.frs.application.repository;

import com.frs.application.domain.FollowAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowAccountRepository extends JpaRepository<FollowAccount, Long>, JpaSpecificationExecutor<FollowAccount> {
}
