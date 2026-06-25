package com.library;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class LibraryManagementApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("--- Testing Annotation Configuration ---");
        BookService annotationService = context.getBean(BookService.class);
        System.out.println("Books: " + annotationService.getAvailableBooks());
    }
}
