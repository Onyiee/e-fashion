package com.houseofo.web.service;

import com.houseofo.data.dtos.DressDto;
import com.houseofo.data.model.*;
import com.houseofo.data.repository.DressRepository;
import com.houseofo.data.repository.UserRepository;
import com.houseofo.exceptions.DressException;
import com.houseofo.exceptions.SizeException;
import com.houseofo.exceptions.TypeException;
import com.houseofo.exceptions.UserException;
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
import static org.mockito.Mockito.*;

@Service
@Slf4j
@ExtendWith(MockitoExtension.class)
class DressServiceImplTest {
    @Mock
    DressRepository dressRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    DressServiceImpl dressServiceImpl;

    @Mock
    ModelMapper modelMapper;

    User user;
    User user1;

    Dress dress;


    @BeforeEach
    void setUp() {

        user = new User();
        user1 = new User();

        dress = new Dress();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findDressById() throws DressException {
        //given
        //when
        String id = "001";
        when(dressRepository.findById(anyString())).thenReturn(Optional.of(dress));
        dressServiceImpl.findById(id);

        //then
        verify(dressRepository).findById(id);
    }

    @Test
    void findAllDresses() {
        //when
       dressServiceImpl.findAllDresses();

        //then
        verify(dressRepository).findAll();

    }

    @Test
    void findDressByDesigner() throws UserException {
        //given
        user.setRole(Role.DESIGNER);
        user.setDesignerBrand("cherryBerry");
        when(userRepository.findUserByDesignerBrand(anyString())).thenReturn(Optional.of(user));

        //when
        dressServiceImpl.findDressByDesigner("cherryBerry");

        //then
        ArgumentCaptor<User> dressArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(dressRepository).findDressesByDesigner(dressArgumentCaptor.capture());
         User captorUserValue = dressArgumentCaptor.getValue();
         assertThat(captorUserValue).isEqualTo(user);

    }

    @Test
    void findDressByType() throws TypeException {
        //given
        //when
        dressServiceImpl.findDressByType("FLAY_SKIRT");

        //then
        ArgumentCaptor<Type> dressArgumentCaptor =
                ArgumentCaptor.forClass(Type.class);
        verify(dressRepository)
                .findDressesByType(dressArgumentCaptor.capture());
        Type dressType = dressArgumentCaptor.getValue();
        assertThat(dressType).isEqualTo(Type.FLAY_SKIRT);
    }

    @Test
    void findDressBySize() throws SizeException {
        //given
        Size size = Size.SIZE4;

        //when
        dressServiceImpl.findDressBySize(size.toString());

        //then
        ArgumentCaptor<Size> dressArgumentCaptor
                = ArgumentCaptor.forClass(Size.class);
        verify(dressRepository)
                .findDressesBySize(dressArgumentCaptor.capture());
        Size captorValue = dressArgumentCaptor.getValue();
        assertThat(captorValue).isEqualTo(size);
    }

    @Test
    void deleteDress() throws DressException {
        //given
        String dressId = "001";

        //when
        when(dressRepository.findById(anyString())).thenReturn(Optional.of(dress));

        dressServiceImpl.deleteDress(dressId);

        //then
        verify(dressRepository).delete(dress);

    }

}