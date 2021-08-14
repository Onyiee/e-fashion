package com.houseofo.web.controller;

import com.houseofo.data.dtos.ApiResponse;
import com.houseofo.data.dtos.UserDto;
import com.houseofo.exceptions.UserException;
import com.houseofo.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/client")
    public ResponseEntity<?> signUpClient(@RequestBody @Valid UserDto dto) {
        try{
            UserDto userDto = userService.createUser(dto);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        } catch (UserException userException) {
            ApiResponse response = new ApiResponse(false, userException.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/designer")
    public ResponseEntity<?> signUpDesigner(@RequestBody @Valid UserDto dto) {
        try{
            UserDto userDto = userService.createDesigner(dto);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        } catch (UserException userException) {
            ApiResponse response = new ApiResponse(false, userException.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<?> signUpAdmin(@RequestBody @Valid UserDto dto) {
        try{
            UserDto userDto = userService.createAdmin(dto);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        } catch (UserException userException) {
            ApiResponse response = new ApiResponse(false, userException.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
