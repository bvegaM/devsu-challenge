package com.devsu.challenge.repository.crud;

import com.devsu.challenge.model.Movement;
import com.devsu.challenge.utils.enums.MovementTypeEnum;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovementCrudRepository extends JpaRepository<Movement, Integer> {

    Optional<List<Movement>> findMovementsByAccountNumberAccount(String numberAccount);
    Optional<List<Movement>> findMovementsByAccountClientDni(String dni);
    Optional<List<Movement>> findByAccountClientDniAndMovementDateIsBetween(String dni, Date startDate, Date endDate);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<List<Movement>> findMovementsByAccountNumberAccountAndMovementTypeAndMovementDate(String numberAccount, MovementTypeEnum movementTypeEnum, Date movementDate);
}
