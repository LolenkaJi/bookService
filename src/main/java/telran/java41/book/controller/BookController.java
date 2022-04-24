package telran.java41.book.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import telran.java41.book.dto.AuthorDto;
import telran.java41.book.dto.BookDto;
import telran.java41.book.service.BookService;

@RestController
@AllArgsConstructor

public class BookController {
	
	BookService bookService;
	
	@PostMapping("/book")
	public boolean addBook(@RequestBody BookDto bookDto) {
		return bookService.addBook(bookDto);
	}
	
	@GetMapping("/book/{isbn}")
	public BookDto findBookByIbsn(@PathVariable Long isbn) {
		return bookService.findBookByIbsn(isbn);
	}
	
	@DeleteMapping("/book/{isbn}")
	public BookDto removeBook(@PathVariable Long isbn) {
		return bookService.removeBook(isbn);
	}
	
	@PutMapping("/book/{isbn}/title/{title}")
    public BookDto updateBook(@PathVariable Long isbn,@PathVariable String title) {
    	return bookService.updateBook(isbn, title);
    }
	@GetMapping("/books/author/{author}")
	public Iterable<BookDto> findBooksByAuthor(@PathVariable String author){
		return bookService.findBooksByAuthor(author);
	}
	@GetMapping("/books/publisher/{publisher}")
	public Iterable<BookDto> findBooksByPublisher(@PathVariable String publisher){
		return bookService.findBooksByPublisher(publisher);
	}
	
	@GetMapping("/authors/book/{isbn}")
	public Iterable<AuthorDto> findBookAuthors(@PathVariable Long isbn){
		return bookService.findBookAuthor(isbn);
	}
	
	@GetMapping("/publishers/author/{author}")
	public Iterable<String> findPublishersByAuthor(@PathVariable String author){
		return bookService.findPublishersByAuthor(author);
	}
	
	@DeleteMapping("/author/{author}")
	public AuthorDto removeAuthor(@PathVariable String author) {
		return bookService.removeAuthor(author);
	}

}
