package com.houseofo.web.controller;

import com.houseofo.data.dtos.ApiResponse;
import com.houseofo.data.dtos.DressDto;
import com.houseofo.data.dtos.UserDto;
import com.houseofo.data.model.Role;
import com.houseofo.exceptions.DressException;
import com.houseofo.exceptions.UserException;
import com.houseofo.web.service.UserService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("")
    public ResponseEntity<?>  createUser (@RequestBody UserDto dto){
        try {
            UserDto userDto = userService.createUser(dto);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        }catch (UserException exception){
            ApiResponse response = new ApiResponse(false, exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        try {
            UserDto userDto = userService.findUserById(id);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }catch (UserException exception){
            ApiResponse apiResponse = new ApiResponse(false, exception.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("userRole/{role}")
    public ResponseEntity<?> getUserByRole(@PathVariable Role role){
        List<UserDto> userDtos = userService.findUserByRole(role);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto,@PathVariable String id){
        try {
            userService.updateUser(userDto, id);
            ApiResponse response = new ApiResponse(true, "Updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (UserException exception){
            ApiResponse response = new ApiResponse(false, exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
     try {
         userService.deleteUser(id);
         ApiResponse apiResponse = new ApiResponse(true, "deleted successfully");
         return new ResponseEntity<>(apiResponse, HttpStatus.OK);
     }catch (UserException userException){
         ApiResponse response = new ApiResponse(false, userException.getMessage());
         return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
     }
    }
}
