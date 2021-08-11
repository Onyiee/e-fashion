package com.houseofo.web.service;


import com.houseofo.data.dtos.OrderDto;
import com.houseofo.data.dtos.UserDto;
import com.houseofo.data.model.Role;
import com.houseofo.data.model.User;
import com.houseofo.exceptions.UserException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    UserDto createUser(UserDto userDto) throws UserException;
    UserDto createDesigner(UserDto userDto) throws UserException;
    UserDto createAdmin(UserDto userDto) throws UserException;
    UserDto findUserById(String id) throws UserException;
    List<UserDto> findUserByRole(Role role);
    void updateUser(UserDto userDto, String id) throws UserException;
    void deleteUser(String id) throws UserException;
    User internalFindUserById(String id) throws UserException;
    List<UserDto> findAllUsers();
    Optional<UserDto>  findUserByName(String userName) throws UserException;
    //todo add findAll method here, impl and controller
}
