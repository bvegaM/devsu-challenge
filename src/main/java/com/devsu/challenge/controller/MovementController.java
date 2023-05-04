package com.devsu.challenge.controller;

import com.devsu.challenge.config.aop.LogExecutionTime;
import com.devsu.challenge.dto.movement.MovementDTO;
import com.devsu.challenge.dto.movement.MovementRequestDTO;
import com.devsu.challenge.service.interfaces.MovementService;
import com.devsu.challenge.utils.Constants;
import com.devsu.challenge.utils.ResponseHandler;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@Slf4j
@Api(tags = "Movement API")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @GetMapping()
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<List<MovementDTO>>> findAll(){
        ResponseHandler<List<MovementDTO>> responseHandler;
        try{
            responseHandler = ResponseHandler.<List<MovementDTO>>builder().code(Constants.STATUS_OK.value())
                    .value(movementService.findAll()).build();

            return new ResponseEntity<>(responseHandler, Constants.STATUS_OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.<List<MovementDTO>>builder().code(Constants.BAD_REQUEST.value())
                    .value(null).build();

            log.error("Finished execution method findAll with error {}", e.getMessage());
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }

    @GetMapping("/numeroCuenta/{numeroCuenta}")
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<List<MovementDTO>>> findByAccountNumberAccount(@PathVariable("numeroCuenta") String numeroCuenta){
        ResponseHandler<List<MovementDTO>> responseHandler;
        try{
            responseHandler = ResponseHandler.<List<MovementDTO>>builder().code(Constants.STATUS_OK.value())
                    .value(movementService.findMovementsByAccountNumberAccount(numeroCuenta)).build();

            return new ResponseEntity<>(responseHandler, Constants.STATUS_OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.<List<MovementDTO>>builder().code(Constants.BAD_REQUEST.value())
                    .value(null).build();

            log.error("Finished execution method findByAccountNumberAccount with error {}", e.getMessage());
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }

    @GetMapping("/cedula/{cedula}")
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<List<MovementDTO>>> findByAccountClientDni(@PathVariable("cedula") String dni){
        ResponseHandler<List<MovementDTO>> responseHandler;
        try{
            responseHandler = ResponseHandler.<List<MovementDTO>>builder().code(Constants.STATUS_OK.value())
                    .value(movementService.findMovementsByAccountClientDni(dni)).build();

            return new ResponseEntity<>(responseHandler, Constants.STATUS_OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.<List<MovementDTO>>builder().code(Constants.BAD_REQUEST.value())
                    .value(null).build();

            log.error("Finished execution method findByAccountClientDni with error {}", e.getMessage());
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }

    @PostMapping()
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<Object>> save(@RequestBody() MovementRequestDTO movementRequestDTO){
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(Constants.CREATED_SUCCESS.value())
                    .value(movementService.save(movementRequestDTO)).build();

            return new ResponseEntity<>(responseHandler, Constants.CREATED_SUCCESS);
        }catch (ConstraintViolationException ev){
            List<String> messages = ev.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();

            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(messages).build();

            log.error("Finished execution method save with error  - validation data {}", ev.getMessage());
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        } catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();

            log.error("Finished execution method save with error {}", e.getMessage());
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }
}
