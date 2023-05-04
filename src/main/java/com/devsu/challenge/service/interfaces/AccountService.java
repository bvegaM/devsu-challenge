package com.devsu.challenge.service.interfaces;

import com.devsu.challenge.dto.AccountDTO;
import com.devsu.challenge.utils.exceptions.DevsuException;

import java.util.List;


public interface AccountService {

    /**
     * Metodo para obtener todas las cuentas
     * @return List<AccountDTO>
     * @throws DevsuException Excepcion Controlada
     */
    List<AccountDTO> findAll() throws DevsuException;

    /**
     * Metodo para obtener una cuenta por id
     * @param id identificador de la cuenta
     * @return AccountDTO
     * @throws DevsuException Excepcion Controlada
     */
    AccountDTO findById(Integer id) throws DevsuException;

    /**
     * Metodo para obtener una cuenta por su numero
     * @param numberAccount numero de la cuenta
     * @return AccountDTO
     * @throws DevsuException Excepcion Controlada
     */
    AccountDTO findByNumberAccount(String numberAccount) throws DevsuException;

    /**
     * Metodo para crear una cuenta
     * @param accountDTO body para crear la cuenta
     * @return AccountDTO
     * @throws DevsuException Excepcion Controlada
     */
    AccountDTO save(AccountDTO accountDTO) throws DevsuException;

    /**
     * Metodo para actualizar una cuenta
     * @param numberAccount numero de la cuenta
     * @param accountDTO Body para actualizar la cuenta
     * @return AccountDTO
     * @throws DevsuException Excepcion Controlada
     */
    AccountDTO update(String numberAccount, AccountDTO accountDTO) throws DevsuException;

    /**
     * Metodo para eliminar una cuenta por identificador de la cuenta
     * @param id identificador de la cuenta
     * @return Boolean
     * @throws DevsuException Excepcion Controlada
     */
    Boolean deleteById(Integer id) throws DevsuException;

    /**
     * Metodo para eliminar una cuenta por numero de cuenta
     * @param numberAccount numero de la cuenta
     * @return Boolean
     * @throws DevsuException Excepcion Controlada
     */
    Boolean deleteByNumberAccount(String numberAccount) throws DevsuException;
}
