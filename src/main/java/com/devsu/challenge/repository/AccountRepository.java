package com.devsu.challenge.repository;

import com.devsu.challenge.model.Account;
import com.devsu.challenge.repository.crud.AccountCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {

    @Autowired
    private AccountCrudRepository accountCrudRepository;

    public Optional<List<Account>> findAll(){
        return Optional.of(accountCrudRepository.findAll());
    }

    public Optional<Account> findById(Integer id){
        return accountCrudRepository.findById(id);
    }

    public Optional<Account> findByNumberAccount(String numberAccount){
        return accountCrudRepository.findByNumberAccount(numberAccount);
    }

    public Account save(Account account){
        return accountCrudRepository.save(account);
    }

    public void deleteById(Integer id){
        accountCrudRepository.deleteById(id);
    }

    public void deleteByNumberAccount(String numberAccount){
        accountCrudRepository.deleteByNumberAccount(numberAccount);
    }
}
