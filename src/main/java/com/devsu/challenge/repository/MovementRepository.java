package com.devsu.challenge.repository;

import com.devsu.challenge.model.Movement;
import com.devsu.challenge.repository.crud.MovementCrudRepository;
import com.devsu.challenge.utils.enums.MovementTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class MovementRepository {

    @Autowired
    private MovementCrudRepository movementCrudRepository;

    public Optional<List<Movement>> findAll(){
        return Optional.of(movementCrudRepository.findAll());
    }

    public Optional<List<Movement>> findMovementsByAccountNumberAccount(String numberAccount){
        return movementCrudRepository.findMovementsByAccountNumberAccount(numberAccount);
    }

    public Optional<List<Movement>> findMovementsByAccountClientDni(String dni){
        return movementCrudRepository.findMovementsByAccountClientDni(dni);
    }

    public Optional<List<Movement>> findMoventsByClientDniBetweenDate(String dni, Date startDate, Date endDate){
        return movementCrudRepository.findByAccountClientDniAndMovementDateIsBetween(dni, startDate, endDate);
    }

    public Optional<List<Movement>> findMovementsByAccountNumberAccountAndMovementTypeAndMovementDate(String numberAccount, MovementTypeEnum movementTypeEnum, Date movementDate){
        return movementCrudRepository.findMovementsByAccountNumberAccountAndMovementTypeAndMovementDate(numberAccount,movementTypeEnum,movementDate);
    }

    public Movement save(Movement movement){
        return movementCrudRepository.save(movement);
    }

    public void deleteById(Integer id){
        movementCrudRepository.deleteById(id);
    }
}
