package com.houseofo.web.service;


import com.houseofo.data.dtos.UserDto;
import com.houseofo.data.model.Role;
import com.houseofo.data.model.User;
import com.houseofo.exceptions.UserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface UserService {
    UserDto findUserById(String id) throws UserException;
    List<UserDto> findUserByRole(Role role);
    void updateUser(UserDto userDto, String id) throws UserException;
    void deleteUser(String id) throws UserException;
    User internalFindUserById(String id) throws UserException;
}
