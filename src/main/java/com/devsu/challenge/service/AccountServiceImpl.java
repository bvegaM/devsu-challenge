package com.devsu.challenge.service;

import com.devsu.challenge.config.NonNullBeanProperties;
import com.devsu.challenge.dto.AccountDTO;
import com.devsu.challenge.dto.mapper.AccountMapper;
import com.devsu.challenge.model.Account;
import com.devsu.challenge.model.Client;
import com.devsu.challenge.repository.AccountRepository;
import com.devsu.challenge.repository.ClientRepository;
import com.devsu.challenge.service.interfaces.AccountService;
import com.devsu.challenge.utils.Constants;
import com.devsu.challenge.utils.exceptions.DevsuException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<AccountDTO> findAll() throws DevsuException {
        try{
            Optional<List<Account>> accountList = accountRepository.findAll();
            return accountMapper.toAccountDtos(accountList.orElseGet(ArrayList::new));
        }catch (Exception e){
            log.error("Error en el AccountServiceImpl.findAll");
            throw new DevsuException(e.getMessage(),e);
        }
    }

    @Override
    public AccountDTO findById(Integer id) throws DevsuException {
        try{
            Optional<Account> account = accountRepository.findById(id);
            return accountMapper.toAccountDto(account.orElseThrow(() -> new DevsuException(Constants.ACCOUNT_NOT_FOUND_MESSAGE)));
        }catch (Exception e){
            log.error("Error en el AccountServiceImpl.findById");
            throw new DevsuException(e.getMessage(),e);
        }
    }

    @Override
    public AccountDTO findByNumberAccount(String numberAccount) throws DevsuException {
        try{
            Optional<Account> account = accountRepository.findByNumberAccount(numberAccount);
            return accountMapper.toAccountDto(account.orElseThrow(() -> new DevsuException(Constants.ACCOUNT_NOT_FOUND_MESSAGE)));
        }catch (Exception e){
            log.error("Error en el AccountServiceImpl.findByNumberAccount");
            throw new DevsuException(e.getMessage(),e);
        }
    }

    @Override
    public synchronized AccountDTO save(AccountDTO accountDTO) throws DevsuException {
        try{
            Optional<Client> client = clientRepository.findByDni(accountDTO.getClienteCedula());
            if(client.isEmpty())
                throw new ConstraintViolationException(Constants.CLIENT_NOT_FOUND_MESSAGE, new HashSet<>());

            Account account = accountMapper.toAccount(accountDTO);
            account.setClient(client.get());

            return accountMapper.toAccountDto(accountRepository.save(account));
        }catch (ConstraintViolationException ev){
            log.error("Error en el AccountServiceImpl.save - validacion de datos");
            throw ev;
        }catch (Exception e){
            log.error("Error en el AccountServiceImpl.save");
            throw new DevsuException(e.getMessage(),e);
        }
    }

    @Override
    public AccountDTO update(String numberAccount, AccountDTO accountDTO) throws DevsuException {
        try{
            Account account = accountRepository.findByNumberAccount(numberAccount)
                    .orElseThrow(() -> new DevsuException(Constants.ACCOUNT_NOT_FOUND_MESSAGE));

            if(accountDTO.getClienteCedula() != null){
                Client client = clientRepository.findByDni(accountDTO.getClienteCedula())
                        .orElseThrow(() -> new DevsuException(Constants.CLIENT_NOT_FOUND_MESSAGE));
                account.setClient(client);
            }

            BeanUtilsBean beanUtils = new NonNullBeanProperties();
            beanUtils.copyProperties(account,accountMapper.toAccount(accountDTO));
            return accountMapper.toAccountDto(accountRepository.save(account));

        }catch (Exception e){
            log.error("Error en el AccountServiceImpl.update");
            throw new DevsuException(e.getMessage(),e);
        }
    }

    @Override
    public Boolean deleteById(Integer id) throws DevsuException {
        try{
            if(accountRepository.findById(id).isEmpty())
                throw new DevsuException(Constants.ACCOUNT_NOT_FOUND_MESSAGE);

            accountRepository.deleteById(id);
            return Boolean.TRUE;
        }catch (Exception e){
            log.error("Error en el AccountServiceImpl.deleteById");
            throw new DevsuException(e.getMessage(),e);
        }
    }

    @Override
    public Boolean deleteByNumberAccount(String numberAccount) throws DevsuException {
        try{
            if(accountRepository.findByNumberAccount(numberAccount).isEmpty())
                throw new DevsuException(Constants.ACCOUNT_NOT_FOUND_MESSAGE);

            accountRepository.deleteByNumberAccount(numberAccount);
            return Boolean.TRUE;
        }catch (Exception e){
            log.error("Error en el AccountServiceImpl.deleteByNumberAccount");
            throw new DevsuException(e.getMessage(),e);
        }
    }
}
