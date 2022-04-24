package telran.java41.book.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import telran.java41.book.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepositiry {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Stream<Book> findByAuthorsName(String name) {
		TypedQuery<Book> query = em.createQuery("select b from Book b join b.authors a where a.name=?1", Book.class);
		query.setParameter(1, name);
		return query.getResultStream();
	}

	@Override
	public Stream<Book> findByPublisherPublisherName(String name) {
		TypedQuery<Book> query = em.createQuery("select b from Book b join b.publisher p where p.publisherName=?1", Book.class);
		query.setParameter(1, name);
		return query.getResultStream();
	}

	@Override
	public void deleteByAuthorsName(String authorName) {
		TypedQuery<Book> query = em.createQuery("select b from Book b join b.authors a where a.name=?1",Book.class);
		query.setParameter(1, authorName);
		List<Book> res = query.getResultList();
		for (int i = 0; i < res.size(); i++) {
			em.remove(res.get(i));
		}
	}

	@Override
	public Book save(Book book) {
		em.persist(book);
		return book;
	}

	@Override
	public boolean existsById(long id) {
		Book book = em.find(Book.class, id);
		return book != null;
	}

	@Override
	public Optional<Book> findById(Long id) {
		Book book = em.find(Book.class, id);
		return Optional.ofNullable(book);
	}

	@Override
	public void delete(Book book) {
		em.remove(book);

	}

}
