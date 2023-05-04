package com.devsu.challenge.service;

import com.devsu.challenge.config.NonNullBeanProperties;
import com.devsu.challenge.dto.ClientDTO;
import com.devsu.challenge.dto.mapper.ClientMapper;
import com.devsu.challenge.model.Client;
import com.devsu.challenge.repository.ClientRepository;
import com.devsu.challenge.service.interfaces.ClientService;
import com.devsu.challenge.utils.Constants;
import com.devsu.challenge.utils.exceptions.DevsuException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<ClientDTO> findAll() throws DevsuException {
        try{
            Optional<List<Client>> clientList = clientRepository.findAll();
            return clientMapper.toClientDtos(clientList.orElseGet(ArrayList::new));
        }catch (Exception e){
            log.error("Error en el ClientServiceImpl.findAll");
            throw new DevsuException(e.getMessage(),e);
        }
    }

    @Override
    public ClientDTO findById(Integer id) throws DevsuException{
        try{
            Optional<Client> client = clientRepository.findById(id);
            return clientMapper.toClientDto(client.orElseThrow(() -> new DevsuException(Constants.CLIENT_NOT_FOUND_MESSAGE)));
        }catch (Exception e){
            log.error("Error en el ClientServiceImpl.findById");
            throw new DevsuException(e.getMessage(),e);
        }
    }

    @Override
    public ClientDTO findByDni(String dni) throws DevsuException{
        try{
            Optional<Client> client = clientRepository.findByDni(dni);
            return clientMapper.toClientDto(client.orElseThrow(() -> new DevsuException(Constants.CLIENT_NOT_FOUND_MESSAGE)));
        }catch (Exception e){
            log.error("Error en el ClientServiceImpl.findByDni");
            throw new DevsuException(e.getMessage(),e);
        }
    }

    @Override
    public synchronized ClientDTO save(ClientDTO clientDTO) throws DevsuException{
        try{
            return clientMapper.toClientDto(clientRepository
                    .save(clientMapper.toClient(clientDTO)));
        }catch (ConstraintViolationException ev){
            log.error("Error en el ClientServiceImpl.save - validacion de datos");
            throw ev;
        } catch (Exception e){
            log.error("Error en el ClientServiceImpl.save");
            throw new DevsuException(e.getMessage(),e);
        }
    }

    @Override
    public ClientDTO update(String dni, ClientDTO clientDTO) throws DevsuException {
        try{
            Client client = clientRepository.findByDni(dni)
                    .orElseThrow(() -> new DevsuException(Constants.CLIENT_NOT_FOUND_MESSAGE));

            Client clientRequest = clientMapper.toClient(clientDTO);
            BeanUtilsBean beanUtils = new NonNullBeanProperties();
            beanUtils.copyProperties(client,clientRequest);
            return clientMapper.toClientDto(clientRepository.save(client));
        }catch (Exception e){
            log.error("Error en el ClientServiceImpl.update");
            throw new DevsuException(e.getMessage(),e);
        }
    }

    @Override
    public Boolean deleteById(Integer id) throws DevsuException{
        try{
            if(clientRepository.findById(id).isEmpty())
                throw new DevsuException(Constants.CLIENT_NOT_FOUND_MESSAGE);

            clientRepository.deleteById(id);
            return Boolean.TRUE;

        }catch (Exception e){
            log.error("Error en el ClientServiceImpl.deleteById");
            throw new DevsuException(e.getMessage(),e);

        }
    }

    @Override
    public Boolean deleteByDni(String dni) throws DevsuException{
        try{
            if(clientRepository.findByDni(dni).isEmpty())
                throw new DevsuException(Constants.CLIENT_NOT_FOUND_MESSAGE);

            clientRepository.deleteByDni(dni);
            return Boolean.TRUE;
        }catch (Exception e){
            log.error("Error en el ClientServiceImpl.deleteByDni");
            throw new DevsuException(e.getMessage(),e);
        }
    }
}
