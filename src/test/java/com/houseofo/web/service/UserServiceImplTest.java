package com.houseofo.web.service;

import com.houseofo.data.dtos.UserDto;
import com.houseofo.data.model.Role;
import com.houseofo.data.model.User;
import com.houseofo.data.repository.UserRepository;
import com.houseofo.exceptions.UserException;
import com.houseofo.util.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Service
@Slf4j
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    UserMapper userMapper;


    @InjectMocks
    UserServiceImpl userServiceImpl;


    User user;


    @BeforeEach
    void setUp() {
        user = new User();
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void findUserById() throws UserException {
        //given
        String id = "testId";
        user.setId(id);

        //when
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        UserDto userDto = userServiceImpl.findUserById("id");

        //then
        log.info("userDto -->{}", userDto);
        verify(userRepository).findById("id");

    }

    @Test
    void findUserByRole() {
        //given
        //when
        userServiceImpl.findUserByRole(Role.CLIENT);

        //then
        ArgumentCaptor<Role> captor
                = ArgumentCaptor.forClass(Role.class);
        verify(userRepository).findUsersByRole(captor.capture());
        Role userRole = captor.getValue();

        log.info("userRole->{}",userRole);

        assertThat(userRole).isEqualTo(Role.CLIENT);
    }

    @Test
    void updateUser() throws UserException {
        //given
        String id = "testId";
        user.setId(id);

        //when
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));

        UserDto userDto = new UserDto();

        userServiceImpl.updateUser(userDto, id);

        //then
        verify(userRepository).findById(id);
        verify(userRepository).save(user);
    }

    @Test
    void deleteUser() throws UserException {
        //given
        String id = "testId";
        user.setId(id);

        //when
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));

        //then

        userServiceImpl.deleteUser(id);

        verify(userRepository).findById(id);
        verify(userRepository).delete(user);

        ArgumentCaptor<User> captor
                = ArgumentCaptor.forClass(User.class);
        verify(userRepository).delete(captor.capture());
        User user1 = captor.getValue();


        assertThat(user1).isEqualTo(user);
        log.info("user is -->{}",user);

    }
}