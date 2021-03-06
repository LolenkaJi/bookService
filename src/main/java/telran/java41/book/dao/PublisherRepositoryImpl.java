package telran.java41.book.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import telran.java41.book.model.Publisher;

@Repository
public class PublisherRepositoryImpl implements PublisherRpository {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<String> findPublisherByAuthor(String author) {	
		TypedQuery<String> query = em.createQuery("select distinct p.publisherName from Book b join b.authors a join b.publisher p where a.name=?1",String.class);
		query.setParameter(1, author);		
		return query.getResultList();
	}

	@Override
	public Optional<Publisher> findById(String id) {
		Publisher publisher = em.find(Publisher.class, id);
		return Optional.ofNullable(publisher);
	}

	@Override
	public Publisher save(Publisher publisher) {
		em.persist(publisher);
		return publisher;
	}

}
