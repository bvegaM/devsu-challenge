package com.devsu.challenge.repository.crud;

import com.devsu.challenge.model.Account;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface AccountCrudRepository extends JpaRepository<Account, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Account> findByNumberAccount(String numberAccount);

    @Transactional
    void deleteByNumberAccount(String numberAccount);
}
