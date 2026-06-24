package com.example.exercises;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock private UserRepository userRepository;
    @InjectMocks private UserService userService;
    @Test
    public void testGetUserById_Success() {
        User mockUser = new User(); mockUser.setId(1L); mockUser.setName("John Doe");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        User result = userService.getUserById(1L);
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }
    @Test
    public void testGetUserById_ThrowsExceptionWhenNotFound() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());
        assertNull(userService.getUserById(999L));
    }
}
