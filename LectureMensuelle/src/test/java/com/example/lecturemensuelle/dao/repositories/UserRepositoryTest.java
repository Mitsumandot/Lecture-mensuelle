package com.example.lecturemensuelle.dao.repositories;

import com.example.lecturemensuelle.dao.entities.User;
import lombok.AllArgsConstructor;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest



public class UserRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("Othmanchiguer.321@gmail.com");
        user.setPassword("Othman123");
        user.setUsername("Othman");
        User savedUser =  userRepository.save(user);
        User existUser = testEntityManager.find(User.class, savedUser.getId());
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }
}
