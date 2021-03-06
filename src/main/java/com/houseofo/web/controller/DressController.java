package com.houseofo.web.controller;

import com.houseofo.data.dtos.ApiResponse;
import com.houseofo.data.dtos.DressDto;
import com.houseofo.data.dtos.DressOrderRequest;
import com.houseofo.exceptions.DressException;
import com.houseofo.exceptions.SizeException;
import com.houseofo.exceptions.TypeException;
import com.houseofo.exceptions.UserException;
import com.houseofo.web.service.DressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/dress")
public class DressController {
    //hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyPermission('permission')

    @Autowired
    private DressService dressService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DESIGNER','ROLE_CLIENT')")
    public ResponseEntity<?> getAllDresses() {
        List<DressDto> dressDtos = dressService.findAllDresses();
        return new ResponseEntity<>(dressDtos, HttpStatus.OK);
    }

    @PostMapping("{designerId}")
    @PreAuthorize("hasAnyAuthority('dress:write')")
    public ResponseEntity<?> createADress(@PathVariable String designerId, @RequestBody @Valid DressDto dto) {
        try {
            DressDto dressDto = dressService.createDress(designerId, dto);
            return new ResponseEntity<>(dressDto, HttpStatus.CREATED);
        } catch (DressException | UserException exception) {
            ApiResponse response = new ApiResponse(false, exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DESIGNER','ROLE_CLIENT')")
    public ResponseEntity<?> getDressById(@PathVariable String id) {
        try {
            DressDto dressDto = dressService.findById(id);
            return new ResponseEntity<>(dressDto, HttpStatus.OK);
        } catch (DressException e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAnyAuthority('dress:write')")
    public ResponseEntity<?> updateDressDetails(@PathVariable String id, @RequestBody @Valid DressDto updateContent) {
        try {
            dressService.updateDress(id, updateContent);
            ApiResponse response = new ApiResponse(true, "updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DressException exception) {
            ApiResponse response = new ApiResponse(false, exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('dress:write')")
    public ResponseEntity<?> deleteDress(@PathVariable String id) {
        try {
            dressService.deleteDress(id);
            ApiResponse apiResponse = new ApiResponse(true, "successfully deleted");
            return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
        } catch (DressException exception) {
            ApiResponse apiResponse = new ApiResponse(false, exception.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("userName/{designerBrand}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DESIGNER','ROLE_CLIENT')")
    public ResponseEntity<?> getDressByDesigner(@PathVariable String designerBrand) {
        try {
            List<DressDto> dressDtos = dressService.findDressByDesigner(designerBrand);
            return new ResponseEntity<>(dressDtos, HttpStatus.OK);
        } catch (UserException exception) {
            ApiResponse response = new ApiResponse(false, exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("Type/{typeName}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DESIGNER','ROLE_CLIENT')")
    public ResponseEntity<?> getDressByType(@PathVariable String typeName) {
        try {
            List<DressDto> dtoList = dressService.findDressByType(typeName);
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        } catch (TypeException e) {
            ApiResponse response = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("Size/{size}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DESIGNER','ROLE_CLIENT')")
    public ResponseEntity<?> getDressBySize(@PathVariable String size) {
        try {
            List<DressDto> dressDtos = dressService.findDressBySize(size);
            return new ResponseEntity<>(dressDtos, HttpStatus.OK);
        } catch (SizeException e) {
            ApiResponse response = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/buy")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DESIGNER','ROLE_CLIENT')")
    public ResponseEntity<?> BuyDress(@RequestBody DressOrderRequest request) {
        try {
            dressService.buyDress(request);
            return new ResponseEntity<>(new ApiResponse(true, "success"), HttpStatus.OK);
        } catch (UserException | DressException e) {
            ApiResponse response = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(field, errorMessage);
        });
        return errors;
    }
}
