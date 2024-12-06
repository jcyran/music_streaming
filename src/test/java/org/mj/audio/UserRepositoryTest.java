package org.mj.audio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mj.audio.model.UserModelIP;
import org.mj.audio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        String username = "admin1";
        UserModelIP user = new UserModelIP();
        user.setUsername(username);
        user.setHashPassword("$2a$10$jCS5HYn8I64Ia.z370j0feFa8X8w/2quUsgU.XvLmvODYYs3X/El6");
        user.setUserRole("ADMIN");
        userRepository.save(user);

        Optional<UserModelIP> retrievedUser = userRepository.findByUsername(username);
        assertTrue(retrievedUser.isPresent());
        assertEquals(username, retrievedUser.get().getUsername());
    }
}
