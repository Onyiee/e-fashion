package com.houseofo.data.repository;

import com.houseofo.data.model.Role;
import com.houseofo.data.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;


import java.util.List;


import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

@DataMongoTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    User user;
    User user2;
    User user3;

    @BeforeEach
    void setUp() {
        user = new User();
        user2 = new User();
        user3 =  new User();
    }

    @Test
    void findUsersByRole() {

       user.setRole(Role.CLIENT);
       userRepository.save(user);

       user2.setRole(Role.CLIENT);
       userRepository.save(user2);

       user3.setRole(Role.ADMIN);
       userRepository.save(user3);


       List<User> users  = userRepository.findUsersByRole(Role.CLIENT);
        assertThat(users.size()).isEqualTo(2);
        assertThat(users.get(0).getRole()).isEqualTo(Role.CLIENT);
       assertThat(users.get(1).getRole()).isEqualTo(Role.CLIENT);

    }
}