package telran.java41.book.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.java41.book.model.Publisher;

public interface PublisherRpository  {
//	@Query("select distinct p.publisherName from Book b join b.authors a join b.publisher p where a.name=?1")
	List<String> findPublisherByAuthor(String author);

	Optional<Publisher> findById(String id);

	Publisher save(Publisher publisher);

}
