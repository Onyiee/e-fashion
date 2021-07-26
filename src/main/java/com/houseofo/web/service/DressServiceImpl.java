package com.houseofo.web.service;


import com.houseofo.data.dtos.DressDto;
import com.houseofo.data.dtos.DressOrderRequest;
import com.houseofo.data.model.*;
import com.houseofo.data.repository.DressRepository;
import com.houseofo.data.repository.UserRepository;
import com.houseofo.exceptions.DressException;
import com.houseofo.exceptions.SizeException;
import com.houseofo.exceptions.TypeException;
import com.houseofo.exceptions.UserException;
import lombok.extern.slf4j.Slf4j;;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DressServiceImpl implements DressService {
    @Autowired
    DressRepository dressRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;


    @Override
    public DressDto createDress(String designerId, DressDto dressDto) throws DressException, UserException {
        //todo add new dress to list of dresses on designer model;
        Dress newDress = modelMapper.map(dressDto, Dress.class);
        User designer = userService.internalFindUserById(designerId);
        newDress.setDesigner(designer);
        if (dressRepository.findDressByDressName(newDress.getDressName()).isPresent()) {
            throw new DressException("Dress already exists.");
        }
        Dress dress = dressRepository.save(newDress);
        return modelMapper.map(dress, DressDto.class);
    }


    @Override
    public DressDto findById(String id) throws DressException {
        Dress dress = dressRepository.findById(id)
                .orElseThrow(() -> new DressException("Id does not match any dress"));
        DressDto dto = modelMapper.map(dress, DressDto.class);
        return dto;
    }

    @Override
    public void updateDress(String id, DressDto updateContent) throws DressException {
        Dress dress = dressRepository.findById(id)
                .orElseThrow(() -> new DressException("Id does not match any dress"));
        modelMapper.map(updateContent, dress);
//        dressMapper.updateDressFromDto(updateContent, dress);
        dressRepository.save(dress);
    }

    @Override
    public void deleteDress(String id) throws DressException {
        Dress dress = dressRepository.findById(id)
                .orElseThrow(() -> new DressException("Id does not match any dress"));
        dressRepository.delete(dress);
    }

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
                .orElseThrow(() -> new UserException("No matching designer found."));
        {
            List<Dress> dressesByDesigner = dressRepository.findDressesByDesigner(user);
            List<DressDto> dressDtos = dressesByDesigner
                    .stream()
                    .map(dress -> modelMapper.map(dress, DressDto.class))
                    .collect(Collectors.toList());
            return dressDtos;
        }
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
                List<Dress> dresses = dressRepository.findDressesBySize(size1);
                List<DressDto> dressDtos = dresses.stream()
                        .map(dress -> modelMapper.map(
                                dress, DressDto.class
                        )).collect(Collectors.toList());

                return dressDtos;
            }
        }
        throw new SizeException("Invalid size. Enter a valid size");
    }

    @Override
    public void buyDress(DressOrderRequest request) throws DressException, UserException {
        String userId = request.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("Id does not match any user"));
        Order order = new Order();
        order.setUser(user);
//        order.setAddress(user.getAddresses().get(0));
        Map<String, Integer> orderDetails = request.getDressOrderDetails();
        for (String dressId : orderDetails.keySet()) {
            Dress dress = dressRepository.findById(dressId)
                    .orElseThrow(() -> new DressException("No dress found with id " + dressId));
            Item item = new Item();
            item.setDress(dress);
            int dressQty = orderDetails.get(dressId);
            item.setQuantity(dressQty);
            BigDecimal dressPrice = dress.getPrice();
            BigDecimal quantity = BigDecimal.valueOf(dressQty);
            item.setTotalPrice(dressPrice.multiply(quantity));
            order.getItems().put(dressId, item);
        }
        orderService.saveOrder(order);
    }


}
