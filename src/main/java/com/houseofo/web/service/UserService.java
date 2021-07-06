package com.houseofo.web.service;


import com.houseofo.data.model.Role;
import com.houseofo.data.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface UserService {
    List<User> findUserById(String id);
    List<User> findUserByRole(Role role);
    User updateUser(String id);
    void deleteUser(String id);
}
