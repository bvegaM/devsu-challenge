package com.devsu.challenge.service.interfaces;

import com.devsu.challenge.dto.movement.MovementDTO;
import com.devsu.challenge.dto.movement.MovementRequestDTO;
import com.devsu.challenge.dto.movement.ReportDTO;
import com.devsu.challenge.utils.exceptions.DevsuException;

import java.util.List;

public interface MovementService {

    /**
     * Metodo que obtiene toda la lista de movimientos
     * @return List<MovementDTO>
     * @throws DevsuException Excepcion Controlada
     */
    List<MovementDTO> findAll() throws DevsuException;

    /**
     * Metodo que obtiene la lista de movimientos por numero de cuenta
     * @param numberAccount numero de cuenta
     * @return List<MovementDTO>
     * @throws DevsuException Excepcion Controlada
     */
    List<MovementDTO> findMovementsByAccountNumberAccount(String numberAccount) throws DevsuException;

    /**
     * Metodo que obtiene la lista de movimientos por documento del cliente
     * @param dni documento del cliente
     * @return List<MovementDTO>
     * @throws DevsuException Excepcion Controlada
     */
    List<MovementDTO> findMovementsByAccountClientDni(String dni) throws DevsuException;

    /**
     * Metodo que obtiene la lista de movimientos por documento del cliente
     * @param dni documento del cliente
     * @param startDate fecha de inicio de busqueda
     * @param endDate fecha final de busqueda
     * @return List<MovementDTO>
     * @throws DevsuException Excepcion Controlada
     */
    List<ReportDTO> findMoventsByClientDniBetweenDate(String dni, String startDate, String endDate) throws DevsuException;

    /**
     * Metodo que permite crear un movimiento
     * @param movementRequestDTO body para crear el movimiento
     * @return MovementDTO
     * @throws DevsuException Excepcion Controlada
     */
    MovementDTO save(MovementRequestDTO movementRequestDTO) throws DevsuException;

    /**
     * Metodo que permite eliminar un movimiento por su id
     * @param id identificador del movimiento
     * @return Boolean
     * @throws DevsuException Excepcion Controlada
     */
    Boolean deleteById(Integer id) throws DevsuException;

}
