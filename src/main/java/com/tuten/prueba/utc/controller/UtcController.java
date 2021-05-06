package com.tuten.prueba.utc.controller;


import com.tuten.prueba.utc.entity.UtcRequest;
import com.tuten.prueba.utc.entity.UtcResponse;
import com.tuten.prueba.utc.service.UtcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utc")
@CrossOrigin("*")
public class UtcController {

    @Autowired
    UtcService utcService;

    @PostMapping("")
    public ResponseEntity<UtcResponse> postUtc(@RequestBody UtcRequest request) {
        try {
            UtcResponse response = utcService.setUTC(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
