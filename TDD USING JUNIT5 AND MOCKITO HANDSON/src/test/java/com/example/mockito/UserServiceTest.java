package com.example.mockito;

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

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserById_RepositoryMock() {
        // Arrange
        User mockUser = new User();
        mockUser.setId(5L);
        mockUser.setName("Sarah Smith");

        when(userRepository.findById(5L)).thenReturn(Optional.of(mockUser));

        // Act
        User result = userService.getUserById(5L);

        // Assert
        assertNotNull(result);
        assertEquals(5L, result.getId());
        assertEquals("Sarah Smith", result.getName());
        verify(userRepository, times(1)).findById(5L);
    }
}
