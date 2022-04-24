package telran.java41.book.service;

import telran.java41.book.dto.AuthorDto;
import telran.java41.book.dto.BookDto;

public interface BookService {
	
	boolean addBook(BookDto bookDto);
	
	BookDto findBookByIbsn(Long isbn);
	
	BookDto removeBook(Long isbn);
	
	BookDto updateBook(Long isbn, String title);
	
	Iterable<BookDto> findBooksByAuthor(String author);
	
	Iterable<BookDto> findBooksByPublisher(String publisher);
	
	Iterable<AuthorDto> findBookAuthor(Long isbn);
	
	Iterable<String> findPublishersByAuthor(String author);
	
	AuthorDto removeAuthor(String author);
	
	

}
