package com.houseofo.data.repository;

import com.houseofo.data.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static com.houseofo.data.model.Size.SIZE2;
import static com.houseofo.data.model.Size.SIZE8;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@DataMongoTest
@Slf4j
class DressRepositoryTest {
    @Autowired
    DressRepository dressRepository;
    @Autowired
    UserRepository userRepository;

    Dress dress;
    Dress dress2;

    User user;
    User user2;

    @BeforeEach
    void setUp() {
        dress = new Dress();
        dress2 = new Dress();

        user = new User();
        user.setDesignerBrand("lavish");
        user2 = new User();
    }


    @Test
    void findDressesByDesigner() {
        user.setRole(Role.DESIGNER);
        user2.setRole(Role.DESIGNER);
        userRepository.save(user);
        userRepository.save(user2);

        dress.setDesigner(user);

        dressRepository.save(dress);

        dress2.setDesigner(user2);
        dressRepository.save(dress2);

        List<Dress> dresses = dressRepository.findDressesByDesigner(user);
        assertThat(dresses.size()).isEqualTo(1);
        log.info("dress -> {}",dresses.get(0).getDesigner().toString());
        assertThat(dresses.get(0).getDesigner().getDesignerBrand()).isEqualTo(user.getDesignerBrand());

    }

    @Test
    void findDressesBySize() {
        dress.setSize(SIZE8);
        dressRepository.save(dress);

        dress2.setSize(SIZE2);
        dressRepository.save(dress2);

        List<Dress> dressList = dressRepository.findDressesBySize(SIZE8);
        List<Dress> dresses = dressRepository.findDressesBySize(SIZE2);
        assertThat(dressList.get(0).getSize()).isEqualTo(SIZE8);
        assertThat(dresses.get(0).getSize()).isEqualTo(SIZE2);
    }

    @Test
    void findDressesByType() {
        dress.setType(Type.PALAZZO_TROUSERS);
        dressRepository.save(dress);

        dress2.setType(Type.BALL_GOWN);
        dressRepository.save(dress2);

        List<Dress> dresses = dressRepository.findDressesByType(Type.BALL_GOWN);
        assertThat(dresses.get(0).getType()).isEqualTo(Type.BALL_GOWN);

        List<Dress> dressList = dressRepository.findDressesByType(Type.PALAZZO_TROUSERS);
        assertThat(dressList.get(0).getType()).isEqualTo(Type.PALAZZO_TROUSERS);

    }
}