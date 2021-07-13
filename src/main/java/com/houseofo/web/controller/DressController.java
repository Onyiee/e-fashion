package com.houseofo.web.controller;

import com.houseofo.data.dtos.DressDto;
import com.houseofo.web.service.DressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dress")
public class DressController {
    @Autowired
    private DressService dressService;

    public ResponseEntity<?> getAllDresses() {
        List<DressDto> dressDtos = dressService.findAllDresses();
//        return new ResponseEntity<>(dressDtos, HttpStatus.OK);
        return ResponseEntity.ok(dressDtos);

    }


}
