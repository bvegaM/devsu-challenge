package com.devsu.challenge.service;

import com.devsu.challenge.dto.mapper.MovementMapper;
import com.devsu.challenge.dto.mapper.ReportMapper;
import com.devsu.challenge.dto.movement.MovementDTO;
import com.devsu.challenge.dto.movement.MovementRequestDTO;
import com.devsu.challenge.dto.movement.ReportDTO;
import com.devsu.challenge.model.Account;
import com.devsu.challenge.model.Movement;
import com.devsu.challenge.repository.AccountRepository;
import com.devsu.challenge.repository.MovementRepository;
import com.devsu.challenge.service.interfaces.MovementService;
import com.devsu.challenge.utils.Constants;
import com.devsu.challenge.utils.Util;
import com.devsu.challenge.utils.enums.MovementTypeEnum;
import com.devsu.challenge.utils.exceptions.DevsuException;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@Service
@Transactional
@Slf4j
public class MovementServiceImpl implements MovementService {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private MovementMapper movementMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<MovementDTO> findAll() throws DevsuException {
        try{
            Optional<List<Movement>> movementList = movementRepository.findAll();
            return movementMapper.toMovementDtos(movementList.orElseGet(ArrayList::new));
        }catch (Exception e){
            log.error("Error en el MovementServiceImpl.findAll");
            throw new DevsuException("Error en MovementServiceImpl.findAll",e);
        }
    }

    @Override
    public List<MovementDTO> findMovementsByAccountNumberAccount(String numberAccount) throws DevsuException {
        try{
            Optional<List<Movement>> movementList = movementRepository.findMovementsByAccountNumberAccount(numberAccount);
            return movementMapper.toMovementDtos(movementList.orElseGet(ArrayList::new));
        }catch (Exception e){
            log.error("Error en el MovementServiceImpl.findMovementsByAccountNumberAccount");
            throw new DevsuException("Error en MovementServiceImpl.findMovementsByAccountNumberAccount",e);
        }
    }

    @Override
    public List<MovementDTO> findMovementsByAccountClientDni(String dni) throws DevsuException {
        try{
            Optional<List<Movement>> movementList = movementRepository.findMovementsByAccountClientDni(dni);
            return movementMapper.toMovementDtos(movementList.orElseGet(ArrayList::new));
        }catch (Exception e){
            log.error("Error en el MovementServiceImpl.findMovementsByAccountClientDni");
            throw new DevsuException("Error en MovementServiceImpl.findMovementsByAccountClientDni",e);
        }
    }

    @Override
    public List<ReportDTO> findMoventsByClientDniBetweenDate(String dni, String startDate, String endDate) throws DevsuException {
        try{
            Optional<List<Movement>> movementList = movementRepository.findMoventsByClientDniBetweenDate(dni, Util.convertStringToDate(startDate),
                    Util.convertStringToDate(endDate));
            return reportMapper.toReportDtos(movementList.orElseGet(ArrayList::new));
        }catch (Exception e){
            log.error("Error en el MovementServiceImpl.findMovementsByAccountClientDni");
            throw new DevsuException("Error en MovementServiceImpl.findMovementsByAccountClientDni",e);
        }
    }

    @Override
    public Boolean deleteById(Integer id) throws DevsuException {
        try{
            movementRepository.deleteById(id);
            return Boolean.TRUE;
        }catch (Exception e){
            log.error("Error en el MovementServiceImpl.deleteById");
            throw new DevsuException("Error en MovementServiceImpl.deleteById",e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public synchronized MovementDTO save(MovementRequestDTO movementRequestDTO) throws DevsuException {
        MovementDTO movementDTO;
        try{
            Account account = this.validateMovementAccount(movementRequestDTO);

            if(MovementTypeEnum.DEBITO.equals(movementRequestDTO.getTipoMovimiento())){
                movementDTO = this.makeDebit(movementRequestDTO, account);
            }else{
                movementDTO = this.makeDeposit(movementRequestDTO, account);
            }
            return movementDTO;
        }catch (ConstraintViolationException ev){
            log.error("Error en el MovementServiceImpl.save - validacion de datos");
            throw ev;
        }catch (Exception e){
            log.error("Error en el MovementServiceImpl.save");
            throw new DevsuException(e.getMessage(),e);
        }
    }

    protected Account validateMovementAccount(MovementRequestDTO movementRequestDTO) throws DevsuException {
        Optional<Account> account = accountRepository.findByNumberAccount(movementRequestDTO.getNumeroCuenta());
        if(account.isEmpty())
            throw new DevsuException("Cuenta no encontrada");
        return account.get();
    }

    protected MovementDTO makeDebit(MovementRequestDTO movementRequestDTO, Account account) throws DevsuException, ParseException {
        try{
            BigDecimal newBalance = account.getInitialBalance().subtract(movementRequestDTO.getValor());

            this.validateMovementValue(movementRequestDTO.getValor());

            if (newBalance.compareTo(BigDecimal.ZERO) <= 0)
                throw new DevsuException("Saldo no disponible");

            this.validateLimiteDebit(movementRequestDTO);

            Movement movement = Movement.builder().balanceInitial(account.getInitialBalance()).balanceAvailable(newBalance)
                    .value(movementRequestDTO.getValor().multiply(BigDecimal.valueOf(-1)))
                    .movementDate(Util.getCurrentDate()).movementType(movementRequestDTO.getTipoMovimiento()).account(account).state(true)
                    .build();

            movementRepository.save(movement);

            this.updateNewBalanceAccount(account, newBalance);

            return movementMapper.toMovementDto(movement);
        }catch (ConstraintViolationException ev){
            log.error("Error en el MovementServiceImpl.realizarDebito - validacion de datos");
            throw ev;
        }catch (Exception e){
            log.error("Error en el MovementServiceImpl.realizarDebito");
            throw e;
        }
    }

    protected void validateLimiteDebit(MovementRequestDTO movementRequestDTO) throws DevsuException, ParseException {
        Optional<List<Movement>> movementList = movementRepository.findMovementsByAccountNumberAccountAndMovementTypeAndMovementDate(movementRequestDTO.getNumeroCuenta(),
                movementRequestDTO.getTipoMovimiento(),
                Util.getCurrentDate());

        BigDecimal totalValue = movementList
                .orElse(Collections.emptyList())
                .stream()
                .map(Movement::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if(totalValue.abs().compareTo(Constants.DEBIT_LIMIT) > 0 || movementRequestDTO.getValor().compareTo(Constants.DEBIT_LIMIT) > 0)
            throw new DevsuException("Cupo diario Excedido");
    }

    protected MovementDTO makeDeposit(MovementRequestDTO movementRequestDTO, Account account) throws DevsuException, ParseException {
        try{
            BigDecimal newBalance = account.getInitialBalance().add(movementRequestDTO.getValor());

            this.validateMovementValue(movementRequestDTO.getValor());

            Movement movement = Movement.builder().balanceInitial(account.getInitialBalance()).balanceAvailable(newBalance)
                    .value(movementRequestDTO.getValor())
                    .movementDate(Util.getCurrentDate()).movementType(movementRequestDTO.getTipoMovimiento()).account(account).state(true)
                    .build();

            movementRepository.save(movement);

            this.updateNewBalanceAccount(account, newBalance);

            return movementMapper.toMovementDto(movement);
        }catch (ConstraintViolationException ev){
            log.error("Error en el MovementServiceImpl.realizarCredito - validacion de datos");
            throw ev;
        }catch (Exception e){
            log.error("Error en el MovementServiceImpl.realizarCredito");
            throw e;
        }
    }

    protected void validateMovementValue(BigDecimal value) throws DevsuException {
        if (value.compareTo(BigDecimal.ZERO) <= 0)
            throw new DevsuException("Valor de movimiento invalido");
    }

    protected void updateNewBalanceAccount(@NotNull Account account, BigDecimal balance){
        account.setInitialBalance(balance);
        accountRepository.save(account);
    }
}
