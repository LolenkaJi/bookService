package telran.java41.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java41.book.model.Author;
import telran.java41.book.model.Book;
import telran.java41.book.model.Publisher;

public interface BookRepositiry{
	
	Stream<Book> findByAuthorsName(String name);
	
	Stream<Book> findByPublisherPublisherName(String name);
	
	void deleteByAuthorsName(String authorName);
	
	Book save(Book book);

	boolean existsById(long isbn);
	
	Optional<Book> findById(Long id);

	void delete(Book book);
	


}
