package com.library.controller;
import com.library.entity.Book;
import com.library.repository.BookBootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookBootRepository bookRepository;
    @PostMapping
    public Book createBook(@RequestBody Book book) { return bookRepository.save(book); }
    @GetMapping
    public List<Book> getAllBooks() { return bookRepository.findAll(); }
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            return bookRepository.save(book);
        }).orElseGet(() -> { updatedBook.setId(id); return bookRepository.save(updatedBook); });
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) { bookRepository.deleteById(id); }
}
