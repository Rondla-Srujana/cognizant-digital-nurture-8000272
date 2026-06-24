package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MyServiceTest {
    @Test
    public void testExternalApi() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");

        MyService service = new MyService(mockApi);
        String result = service.fetchData();
        assertEquals("Mock Data", result);
    }

    @Test
    public void testVerifyInteraction() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        service.fetchData();
        verify(mockApi).getData();
    }

    @Test
    public void testArgumentMatching() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        service.processAndSave("Specific Argument");
        verify(mockApi).saveData(eq("Specific Argument"));
    }

    @Test
    public void testHandlingVoidMethods() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        doNothing().when(mockApi).saveData(anyString());
        service.processAndSave("Test Void");
        verify(mockApi).saveData("Test Void");
    }

    @Test
    public void testMultipleReturns() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("First Call").thenReturn("Second Call");

        MyService service = new MyService(mockApi);
        assertEquals("First Call", service.fetchData());
        assertEquals("Second Call", service.fetchData());
    }

    @Test
    public void testVerificationOrder() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        service.fetchData();
        service.processAndSave("Ordered Data");

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).saveData("Ordered Data");
    }

    @Test
    public void testVoidMethodWithException() throws Exception {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        doThrow(new RuntimeException("API Failure")).when(mockApi).doSomething();

        assertThrows(RuntimeException.class, () -> {
            service.executeAction();
        });
    }
}