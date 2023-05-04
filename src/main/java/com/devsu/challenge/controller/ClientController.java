package com.devsu.challenge.controller;

import com.devsu.challenge.config.aop.LogExecutionTime;
import com.devsu.challenge.dto.ClientDTO;
import com.devsu.challenge.service.interfaces.ClientService;
import com.devsu.challenge.utils.Constants;
import com.devsu.challenge.utils.ResponseHandler;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Slf4j
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<List<ClientDTO>>> findAll(){
        ResponseHandler<List<ClientDTO>> responseHandler;
        try{
            responseHandler = ResponseHandler.<List<ClientDTO>>builder().code(Constants.STATUS_OK.value())
                    .value(clientService.findAll()).build();
            return new ResponseEntity<>(responseHandler, Constants.STATUS_OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.<List<ClientDTO>>builder().code(Constants.BAD_REQUEST.value())
                    .value(null).build();

            log.error("Finished execution method findAll with error {}", e.getMessage());
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<Object>> findById(@PathVariable("id") Integer id){
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(Constants.STATUS_OK.value())
                    .value(clientService.findById(id)).build();

            return new ResponseEntity<>(responseHandler, Constants.STATUS_OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();

            log.error("Finished execution method findById with error {}", e.getMessage());
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/cedula/{dni}")
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<Object>> findByDni(@PathVariable("dni") String dni){
        ResponseHandler<Object> responseHandler;
        try{

            responseHandler = ResponseHandler.builder().code(Constants.STATUS_OK.value())
                    .value(clientService.findByDni(dni)).build();

            return new ResponseEntity<>(responseHandler, Constants.STATUS_OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.builder().code(Constants.BAD_REQUEST.value())
                    .value(e.getMessage()).build();

            log.error("Finished execution method findByDni with error {}", e.getMessage());
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }

    @PostMapping()
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<Object>> save(@RequestBody() ClientDTO clientDTO){
        ResponseHandler<Object> responseHandler;
        try{

            responseHandler = ResponseHandler.builder().code(Constants.CREATED_SUCCESS.value())
                    .value(clientService.save(clientDTO)).build();

            return new ResponseEntity<>(responseHandler, Constants.CREATED_SUCCESS);
        }catch (ConstraintViolationException ev){
            List<String> messages = ev.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();

            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(messages).build();

            log.error("Finished execution method save with error validation data {}", messages);
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        } catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();

            log.error("Finished execution method save with error validation data {}", e.getMessage());
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }

    @PatchMapping("/cedula/{cedula}")
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<Object>> update(@PathVariable("cedula")String dni,@RequestBody() ClientDTO clientDTO){
        ResponseHandler<Object> responseHandler;
        try{

            responseHandler = ResponseHandler.builder().code(Constants.CREATED_SUCCESS.value())
                    .value(clientService.update(dni, clientDTO)).build();

            return new ResponseEntity<>(responseHandler, Constants.CREATED_SUCCESS);
        }catch (ConstraintViolationException ev){
            List<String> messages = ev.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();

            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(messages).build();

            log.error("Finished execution method update with error validation data {}", messages);
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        } catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();

            log.error("Finished execution method update with error {}", e.getMessage());
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }



    @DeleteMapping(value = "/{id}")
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<Boolean>> deleteById(@PathVariable("id") Integer id){
        ResponseHandler<Boolean> responseHandler;
        try{

            responseHandler = ResponseHandler.<Boolean>builder().code(Constants.STATUS_OK.value())
                    .value(clientService.deleteById(id)).build();

            return new ResponseEntity<>(responseHandler, Constants.STATUS_OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.<Boolean>builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(Boolean.FALSE).build();

            log.error("Finished execution method deleteById with error {}", e.getMessage());
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/cedula/{dni}")
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<Boolean>> deleteByDni(@PathVariable("dni") String dni){
        ResponseHandler<Boolean> responseHandler;
        try{

            responseHandler = ResponseHandler.<Boolean>builder().code(HttpStatus.OK.value())
                    .value(clientService.deleteByDni(dni)).build();

            return new ResponseEntity<>(responseHandler, Constants.STATUS_OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.<Boolean>builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(Boolean.FALSE).build();

            log.error("Finished execution method deleteByDni with error {}", e.getMessage());
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }
}
