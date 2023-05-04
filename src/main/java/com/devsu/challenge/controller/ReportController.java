package com.devsu.challenge.controller;

import com.devsu.challenge.config.aop.LogExecutionTime;
import com.devsu.challenge.dto.movement.ReportDTO;
import com.devsu.challenge.service.interfaces.MovementService;
import com.devsu.challenge.utils.Constants;
import com.devsu.challenge.utils.ResponseHandler;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes")
@Slf4j
@Api(tags = "Report API")
public class ReportController {

    @Autowired
    private MovementService movementService;


    @GetMapping()
    @LogExecutionTime
    public ResponseEntity<ResponseHandler<List<ReportDTO>>> findByAccountNumberAccount(@RequestParam List<String> fecha,
                                                                                       @RequestParam String cliente){
        ResponseHandler<List<ReportDTO>> responseHandler;
        try{
            responseHandler = ResponseHandler.<List<ReportDTO>>builder().code(Constants.STATUS_OK.value())
                    .value(movementService.findMoventsByClientDniBetweenDate(cliente,fecha.get(0),fecha.get(1))).build();

            return new ResponseEntity<>(responseHandler, Constants.STATUS_OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.<List<ReportDTO>>builder().code(Constants.BAD_REQUEST.value())
                    .value(null).build();

            log.error("Finished execution method findByAccountNumberAccount with error {}", e.getMessage());
            return new ResponseEntity<>(responseHandler, Constants.BAD_REQUEST);
        }
    }
}
