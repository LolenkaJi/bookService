package telran.java41.book.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import telran.java41.book.dao.AuthorRepository;
import telran.java41.book.dao.BookRepositiry;
import telran.java41.book.dao.PublisherRpository;
import telran.java41.book.dto.AuthorDto;
import telran.java41.book.dto.BookDto;
import telran.java41.book.dto.exceptions.EntityNotFoundException;
import telran.java41.book.model.Author;
import telran.java41.book.model.Book;
import telran.java41.book.model.Publisher;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
	
	BookRepositiry bookRepositiry;
	AuthorRepository authorRepository;
	PublisherRpository publisherRpository;
	ModelMapper modelMapper;

	@Override
	@Transactional
	public boolean addBook(BookDto bookDto) {
		if(bookRepositiry.existsById(bookDto.getIsbn())) {			
			return false;
		}
		Publisher publisher = publisherRpository.findById(bookDto.getPublisher())
				.orElseGet(() -> publisherRpository.save(new Publisher(bookDto.getPublisher())));
		Set<Author>authors = bookDto.getAuthors().stream()
				.map(a -> authorRepository.findById(a.getName())
						.orElseGet(() -> authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
				.collect(Collectors.toSet());	
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
		bookRepositiry.save(book);
		return true;
	}

	@Override
	public BookDto findBookByIbsn(Long isbn) {
		Book book = bookRepositiry.findById(isbn).orElseThrow(() -> new EntityNotFoundException());	
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	@Transactional
	public BookDto removeBook(Long isbn) {
		Book book = bookRepositiry.findById(isbn).orElseThrow(() -> new EntityNotFoundException());
		bookRepositiry.delete(book);
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	@Transactional
	public BookDto updateBook(Long isbn, String title) {
		Book book = bookRepositiry.findById(isbn).orElseThrow(() -> new EntityNotFoundException());
		book.setTitle(title);
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<BookDto> findBooksByAuthor(String author) {
		return bookRepositiry.findByAuthorsName(author)
		.map(b -> modelMapper.map(b, BookDto.class))
		.collect(Collectors.toList());		
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<BookDto> findBooksByPublisher(String publisher) {
		return bookRepositiry.findByPublisherPublisherName(publisher)
				.map(b -> modelMapper.map(b, BookDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<AuthorDto> findBookAuthor(Long isbn) {
		Book book = bookRepositiry.findById(isbn).orElseThrow(() -> new EntityNotFoundException());
		return book.getAuthors().stream()
				.map(a -> modelMapper.map(a, AuthorDto.class))
				.collect(Collectors.toSet());				
	}

	@Override
	public Iterable<String> findPublishersByAuthor(String author) {
		return publisherRpository.findPublisherByAuthor(author);	
	}

	@Override
	@Transactional
	public AuthorDto removeAuthor(String author) {
		Author author1 = authorRepository.findById(author).orElseThrow(() -> new EntityNotFoundException());
		bookRepositiry.deleteByAuthorsName(author);
		authorRepository.delete(author1);	
		return modelMapper.map(author1, AuthorDto.class);
	}

}
