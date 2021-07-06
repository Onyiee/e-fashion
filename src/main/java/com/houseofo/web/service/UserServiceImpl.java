package com.houseofo.web.service;

import com.houseofo.data.dtos.UserDto;
import com.houseofo.data.model.Role;
import com.houseofo.data.model.User;
import com.houseofo.data.repository.UserRepository;
import com.houseofo.exceptions.UserException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDto findUserById(String id) throws UserException {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserException("No user matches the ID passed in."));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public List <UserDto> findUserByRole(Role role) {
        List<User> users = userRepository.findUsersByRole(role);
        List<UserDto> userDtos = users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public User updateUser(String id, UserDto updateContent) {

        return null;
    }

    @Override
    public void deleteUser(String id) {

    }
}
