package com.devsu.challenge.controller;

import com.devsu.challenge.config.aop.LogExecutionTime;
import com.devsu.challenge.dto.AccountDTO;
import com.devsu.challenge.service.interfaces.AccountService;
import com.devsu.challenge.utils.Constants;
import com.devsu.challenge.utils.ResponseHandler;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping()
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<List<AccountDTO>>> findAll(){
        ResponseHandler<List<AccountDTO>> responseHandler;
        try{
            responseHandler = ResponseHandler.<List<AccountDTO>>builder().code(Constants.STATUS_OK.value())
                    .value(accountService.findAll()).build();

            return new ResponseEntity<>(responseHandler, Constants.STATUS_OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.<List<AccountDTO>>builder().code(Constants.BAD_REQUEST.value())
                    .value(null).build();

            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<Object>> findById(@PathVariable("id") Integer id){
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(Constants.STATUS_OK.value())
                    .value(accountService.findById(id)).build();

            return new ResponseEntity<>(responseHandler, Constants.STATUS_OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();

            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }

    @GetMapping("/numeroCuenta/{numeroCuenta}")
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<Object>> findByNumberAccount(@PathVariable("numeroCuenta") String numberAccount){
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(Constants.STATUS_OK.value())
                    .value(accountService.findByNumberAccount(numberAccount)).build();

            return new ResponseEntity<>(responseHandler, Constants.STATUS_OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();

            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }

    @PostMapping()
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<Object>> save(@RequestBody() AccountDTO accountDTO){
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(Constants.CREATED_SUCCESS.value())
                    .value(accountService.save(accountDTO)).build();

            return new ResponseEntity<>(responseHandler, Constants.CREATED_SUCCESS);
        }catch (ConstraintViolationException ev){
            List<String> messages = ev.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();

            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(messages).build();

            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        } catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();

            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }

    @PatchMapping("/numeroCuenta/{numeroCuenta}")
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<Object>> save(@PathVariable("numeroCuenta") String numeroCuenta,@RequestBody() AccountDTO accountDTO){
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(Constants.CREATED_SUCCESS.value())
                    .value(accountService.update(numeroCuenta, accountDTO)).build();

            return new ResponseEntity<>(responseHandler, Constants.CREATED_SUCCESS);
        }catch (ConstraintViolationException ev){
            List<String> messages = ev.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();

            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(messages).build();

            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        } catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();

            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<Boolean>> deleteById(@PathVariable("id") Integer id){
        ResponseHandler<Boolean> responseHandler;
        try{
            responseHandler = ResponseHandler.<Boolean>builder().code(Constants.STATUS_OK.value())
                    .value(accountService.deleteById(id)).build();

            return new ResponseEntity<>(responseHandler, Constants.STATUS_OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.<Boolean>builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(Boolean.FALSE).build();

            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }

    @DeleteMapping("numeroCuenta/{numeroCuenta}")
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<Boolean>> deleteByNumberAccount(@PathVariable("numeroCuenta") String numberAccount){
        ResponseHandler<Boolean> responseHandler;
        try{
            responseHandler = ResponseHandler.<Boolean>builder().code(Constants.STATUS_OK.value())
                    .value(accountService.deleteByNumberAccount(numberAccount)).build();

            return new ResponseEntity<>(responseHandler, Constants.STATUS_OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.<Boolean>builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(Boolean.FALSE).build();

            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }
}
