package com.example.exercises;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserIntegrationTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private UserRepository userRepository;
    @Test
    public void testGetFullFlowFromDb() throws Exception {
        User user = new User(); user.setId(100L); user.setName("Alice");
        userRepository.save(user);
        mockMvc.perform(get("/users/100")).andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Alice"));
    }
}
