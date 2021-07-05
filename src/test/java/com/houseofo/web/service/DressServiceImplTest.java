package com.houseofo.web.service;

import com.houseofo.data.model.*;
import com.houseofo.data.repository.DressRepository;
import com.houseofo.data.repository.UserRepository;
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
import org.springframework.stereotype.Service;

import java.util.List;

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

    Dress dress;
    Dress dress1;

    User user;
    User user1;


    @BeforeEach
    void setUp() {

        dress = new Dress();
        dress1 = new Dress();

        user = new User();
        user1 = new User();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findAllDresses() {
        //given
        user.setRole(Role.DESIGNER);
        user.setSize(Size.SIZE14);
        userRepository.save(user);

        dress.setSize(Size.SIZE8);
        dress.setType(Type.FITTED_GOWN);
        dress.setDesigner(user);

        user1.setRole(Role.DESIGNER);
        user1.setSize(Size.SIZE18);
        userRepository.save(user1);

        dress1.setSize(Size.SIZE2);
        dress1.setType(Type.PALAZZO_TROUSERS);
        dress1.setDesigner(user1);


        //when
        dressRepository.findAll();

        //then
        ArgumentCaptor<Dress> dressArgumentCaptor
                = ArgumentCaptor.forClass(Dress.class);
        verify(dressRepository).findAll();

    }

//    @Test
//    void findDressByDesigner() throws UserException {
//        //given
//        user.setRole(Role.DESIGNER);
//
//        user.setDesignerBrand("cherryBerry");
//
//        dress.setDesigner(user);
//
//        //when
//        dressServiceImpl.findDressByDesigner("cherryBerry");
//
//        //then
//        ArgumentCaptor<User> dressArgumentCaptor =
//                ArgumentCaptor.forClass(User.class);
//        verify(dressRepository).findDressesByDesigner(dressArgumentCaptor.capture());
//
//         User captorUserValue = dressArgumentCaptor.getValue();
//         assertThat(captorUserValue).isEqualTo(user);
//    }

    @Test
    void findDressByType() throws TypeException {
        //given
        dress.setType(Type.FLAY_SKIRT);
        dress1.setType(Type.PALAZZO_TROUSERS);

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
}