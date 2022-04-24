package telran.java41.book.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java41.book.model.Author;
import telran.java41.book.model.Book;
import telran.java41.book.model.Publisher;

public interface AuthorRepository{
	
	Optional<Author> findById(String id);
	
	Author save(Author author);

	void delete(Author author);
	

}
