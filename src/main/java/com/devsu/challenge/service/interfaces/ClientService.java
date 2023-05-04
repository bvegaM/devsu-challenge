package com.devsu.challenge.service.interfaces;

import com.devsu.challenge.dto.ClientDTO;
import com.devsu.challenge.utils.exceptions.DevsuException;

import java.util.List;

public interface ClientService {

    /**
     * Metodo que retorno todos los cliente
     * @return List<ClientDTO>
     * @throws DevsuException excepcion controlada
     */
    List<ClientDTO> findAll() throws DevsuException;

    /**
     * Metodo que retorna un cliente por id
     * @param id identificador del cliente
     * @return ClientDTO
     * @throws DevsuException excepcion controlada
     */

    ClientDTO findById(Integer id) throws DevsuException;

    /**
     * Metodo que retorna un cliente por dni
     * @param dni documento del cliente
     * @return ClientDTO
     * @throws DevsuException excepcion controlada
     */

    ClientDTO findByDni(String dni) throws DevsuException;

    /**
     * Metodo que crea un cliente
     * @param clientDTO body para crear el cliente
     * @return ClientDTO
     * @throws DevsuException excepcion controlada
     */
    ClientDTO save(ClientDTO clientDTO) throws DevsuException;

    /**
     * Metodo para actualizar de forma parcial un cliente
     * @param dni identificador del cliente
     * @param clientDTO Body del cliente a actualizar
     * @return ClientDTO
     * @throws DevsuException
     */
    ClientDTO update(String dni, ClientDTO clientDTO) throws DevsuException;

    /**
     * Metodo para eliminar un cliente por id
     * @param id identificador del cliente
     * @return Boolean
     */
    Boolean deleteById(Integer id) throws DevsuException;

    /**
     * Metodo para eleiminar un cliente por dni
     * @param dni documento del cliente
     * @return Boolean
     * @throws DevsuException excepcion controlada
     */
    Boolean deleteByDni(String dni) throws DevsuException;

}
