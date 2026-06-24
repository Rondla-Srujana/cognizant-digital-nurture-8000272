package com.example.exercises;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired private UserRepository userRepository;
    @Test
    public void testFindByName() {
        User user = new User(); user.setId(10L); user.setName("Charlie");
        userRepository.save(user);
        List<User> result = userRepository.findByName("Charlie");
        assertEquals(1, result.size());
    }
}
