package com.devsu.challenge.repository.crud;

import com.devsu.challenge.model.Client;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface ClientCrudRepository extends JpaRepository<Client, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Client> findByDni(String dni);

    @Transactional
    void deleteByDni(String dni);
}
