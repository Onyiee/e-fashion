package com.houseofo.web.controller;

import com.houseofo.data.dtos.ApiResponse;
import com.houseofo.data.dtos.DressDto;
import com.houseofo.exceptions.DressException;
import com.houseofo.web.service.DressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dress")
public class DressController {
    @Autowired
    private DressService dressService;

    @GetMapping("")
    public ResponseEntity<?> getAllDresses() {
        List<DressDto> dressDtos = dressService.findAllDresses();
        return new ResponseEntity<>(dressDtos, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createADress(@RequestBody DressDto dto){
        DressDto dressDto = dressService.createDress(dto);
        return new ResponseEntity<>(dressDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getDressById(@PathVariable String id){
      try {
          DressDto dressDto = dressService.findById(id);
          return new ResponseEntity<>(dressDto, HttpStatus.OK);
      }catch (DressException e){
          ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
          return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
      }

    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateDressDetails(@PathVariable String id,@RequestBody DressDto updateContent){
        try {
            dressService.updateDress(id, updateContent);
            ApiResponse response = new ApiResponse(true, "updated successfully");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (DressException exception){
            ApiResponse response = new ApiResponse(false, exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDress(@PathVariable String id){
        try {
            dressService.deleteDress(id);
            ApiResponse apiResponse = new ApiResponse(true, "successfully deleted");
            return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
        }catch (DressException exception){
            ApiResponse apiResponse = new ApiResponse(false, exception.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

    }

}
