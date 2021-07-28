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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    Dress dress2;


    @BeforeEach
    void setUp() {

        user = new User();
        user1 = new User();

        dress = new Dress();
        dress2 = new Dress();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void dressCanBeAdded() throws DressException, UserException {
        //given
        DressDto dto = new DressDto();

        //when
        dress = modelMapper.map(dto, Dress.class);

        dressServiceImpl.createDress(dress.getId(), dto);

        //then
        verify(dressRepository).save(dress);

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
    void updateDress() throws DressException {
        //given
        String id = "testId";
        dress.setId(id);

        //when
        when(dressRepository.findById(anyString())).thenReturn(Optional.of(dress));
        DressDto dressDto = new DressDto();

        dressServiceImpl.updateDress(id,dressDto);

        //then
        verify(dressRepository).findById(id);
        verify(dressRepository).save(dress);
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

    @Test
    void testForExceptions(){
        assertThatThrownBy(()-> dressServiceImpl
                .findDressByDesigner("cherryBerry")).hasMessageContaining("No matching designer found.");

    }

}