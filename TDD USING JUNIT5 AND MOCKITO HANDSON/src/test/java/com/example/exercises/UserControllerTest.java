package com.example.exercises;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.NoSuchElementException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private UserService userService;
    @Autowired private ObjectMapper objectMapper;

    @Test
    public void testGetUserById_Success() throws Exception {
        User user = new User(); user.setId(1L); user.setName("Jane Doe");
        when(userService.getUserById(1L)).thenReturn(user);
        mockMvc.perform(get("/users/1")).andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Jane Doe"));
    }
    @Test
    public void testCreateUser_Success() throws Exception {
        User inputUser = new User(); inputUser.setName("Bob");
        User savedUser = new User(); savedUser.setId(2L); savedUser.setName("Bob");
        when(userService.saveUser(any(User.class))).thenReturn(savedUser);
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(inputUser)))
               .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(2));
    }
    @Test
    public void testControllerExceptionHandling_Returns404() throws Exception {
        when(userService.getUserById(404L)).thenThrow(new NoSuchElementException("User not found"));
        mockMvc.perform(get("/users/404")).andExpect(status().isNotFound())
               .andExpect(content().string("User not found"));
    }
}
