package com.houseofo.web.service;

import com.houseofo.data.dtos.UserDto;

import com.houseofo.data.model.Role;
import com.houseofo.data.model.User;
import com.houseofo.data.repository.UserRepository;
import com.houseofo.exceptions.UserException;
import com.houseofo.security.security.ApplicationUserRoles;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder encoder;



    @Override
    public UserDto createUser(UserDto userDto) throws UserException {
        User user= getUserFromDto(userDto);
        user.setGrantedAuthorities(ApplicationUserRoles.CLIENT.getGrantedAuthorities());
        User savedUser = userRepository.save(user);
        //todo address issue fix
        log.info("savedUser -->{}",savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto createDesigner(UserDto userDto) throws UserException {
        UserDto test = new UserDto();
        User user= getUserFromDto(userDto);
        user.setGrantedAuthorities(ApplicationUserRoles.DESIGNER.getGrantedAuthorities());
        User savedUser = userRepository.save(user);
        log.info("savedUser -->{}",savedUser);

        return modelMapper.map(savedUser, UserDto.class);

    }
    @Override
    public UserDto createAdmin(UserDto userDto) throws UserException {
        User user= getUserFromDto(userDto);
        user.setGrantedAuthorities(ApplicationUserRoles.ADMIN.getGrantedAuthorities());
        User savedUser = userRepository.save(user);
        log.info("savedUser -->{}",savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    private User getUserFromDto(UserDto userDto) throws UserException {
        String username = userDto.getUserName();
        if (userRepository.findUserByUserName(username).isPresent()) {
            throw new UserException("user already exists.");
        }
        String brand = userDto.getDesignerBrand();
        if (userRepository.findUserByDesignerBrand(brand).isPresent()) {

            throw new UserException("user already exists with brand " + brand);
        }
        User user = modelMapper.map(userDto, User.class);
        String encryptedPassword = encoder.encode(userDto.getPassword());
        user.setPassword(encryptedPassword);
        return user;
    }

    @Override
    public UserDto findUserById(String id) throws UserException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("No user matches the ID passed in."));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public List<UserDto> findUserByRole(Role role) {
        List<User> users = userRepository.findUsersByRole(role);
        List<UserDto> userDtos = users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return userDtos;
    }


    public void updateUser(UserDto userDto, String id) throws UserException {
        User myUser = userRepository.findById(id)
                .orElseThrow(() -> new UserException("No matching user ID found."));
        modelMapper.map(userDto, myUser);
        userRepository.save(myUser);
    }

    @Override
    public void deleteUser(String id) throws UserException {
        User myUser = userRepository.findById(id)
                .orElseThrow(() -> new UserException("No matching user ID found."));
        userRepository.delete(myUser);
    }

    @Override
    public User internalFindUserById(String id) throws UserException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("No user matches the ID passed in."));
        return modelMapper.map(user, User.class);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtos = userList
                .stream().map(user -> modelMapper.map(
                        user, UserDto.class
                )).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public Optional<UserDto> findUserByName(String userName) throws UserException {
//        User user = userRepository.findUserByUserName(userName)
//                .orElseThrow(() -> new UserException("No user matches the name passed in."));
//
//        UserDto userDto = modelMapper.map(user, UserDto.class);
//        return userDto;
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByUserName(userName)
                .orElseThrow(() -> new UserException("No user matches the name passed in.")));
        Optional<UserDto> userDto = modelMapper.map(userOptional, (Type) UserDto.class);
        return userDto;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository
                  .findUserByUserName(userName)
                .orElseThrow(()->new UsernameNotFoundException(String.format("Username %s not found",userName)));
    }



}
