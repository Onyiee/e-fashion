package com.houseofo.web.service;

import com.houseofo.data.dtos.DressDto;
import com.houseofo.data.model.*;
import com.houseofo.data.repository.DressRepository;
import com.houseofo.data.repository.UserRepository;
import com.houseofo.exceptions.RoleException;
import com.houseofo.exceptions.SizeException;
import com.houseofo.exceptions.TypeException;
import com.houseofo.exceptions.UserException;
import lombok.extern.slf4j.Slf4j;;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DressServiceImpl implements DressService {
    @Autowired
    DressRepository dressRepository;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;


    @Override
    public List<DressDto> findAllDresses() {
        List<Dress> dressList = dressRepository.findAll();
        List<DressDto> dressDtos = dressList
                .stream().map(dress -> modelMapper.map(
                        dress, DressDto.class
                )).collect(Collectors.toList());
        return dressDtos;
    }


    @Override
    public List<DressDto> findDressByDesigner(String designerBrand) throws UserException {
        User user = userRepository.findUserByDesignerBrand(designerBrand)
                .orElseThrow(()-> new UserException("No matching designer found."));

            List<Dress> dressesByDesigner = dressRepository.findDressesByDesigner(user);
            List<DressDto> dressDtos = dressesByDesigner
                    .stream()
                    .map(dress -> modelMapper.map(dress, DressDto.class))
                    .collect(Collectors.toList());
            return dressDtos;


    }

    @Override
    public List<DressDto> findDressByType(String dressTypeString) throws TypeException {
        for (Type type : Type.values()) {
            if (type.name().equalsIgnoreCase(dressTypeString)) {
                List<Dress> dressList = dressRepository.findDressesByType(type);
                List<DressDto> dressDtos = dressList
                        .stream().map(dress -> modelMapper.map(
                                dress, DressDto.class
                        )).collect(Collectors.toList());
                return dressDtos;
            }
        }
        throw new TypeException("No Type found with that name");
    }

    @Override
    public List<DressDto> findDressBySize(String size) throws SizeException {
        for (Size size1 : Size.values()) {
            if (size1.name().equalsIgnoreCase(size)) {
                List<Dress> dresses = dressRepository.findDressesBySize(Size.SIZE6);
                List<DressDto> dressDtos = dresses.stream()
                        .map(dress -> modelMapper.map(
                                dress, DressDto.class
                        )).collect(Collectors.toList());

                return dressDtos;
            }
        }
        throw new SizeException("Invalid size. Enter a valid size");
    }


}
