package ru.itgirl.library.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itgirl.library.model.dto.AuthorDto;
import ru.itgirl.library.model.dto.BookCreateDto;
import ru.itgirl.library.model.dto.BookDto;
import ru.itgirl.library.model.dto.BookUpdateDto;
import ru.itgirl.library.model.entity.Book;
import ru.itgirl.library.model.entity.Genre;
import ru.itgirl.library.repository.AuthorRepository;
import ru.itgirl.library.repository.BookRepository;
import ru.itgirl.library.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public BookDto getByNameV1(String name) {
        Book book = bookRepository.findBookByName(name).orElseThrow();
        return convertEntityToDto(book);
    }

    @Override
    public BookDto getByNameV2(String name) {
        Book book = bookRepository.findBookByNameBySql(name).orElseThrow();
        return convertEntityToDto(book);
    }

    @Override
    public BookDto getByNameV3(String name) {
        Specification<Book> specification = Specification.where(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get("name"), name);
            }
        });

        Book book = bookRepository.findOne(specification).orElseThrow();
        return convertEntityToDto(book);
    }

    private BookDto convertEntityToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .genre(book.getGenre().getName())
                .name(book.getName())
                .build();
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return convertToDto(book);
    }

    private BookDto convertToDto(Book book) {
        List<AuthorDto> authorDtoList = book.getAuthors()
                .stream()
                .map(author -> AuthorDto.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .surname(author.getSurname())
                        .build()
                ).toList();
        return BookDto.builder()
                .authors(authorDtoList)
                .id(book.getId())
                .name(book.getName())
                .genre(book.getGenre().getName())
                .build();
    }

    @Override
    public BookDto createBook(BookCreateDto bookCreateDto) {
        Book book = bookRepository.save(convertDtoToEntity(bookCreateDto));
        BookDto bookDto = convertEntityToDto(book);
        return bookDto;
    }

    private Book convertDtoToEntity(BookCreateDto bookCreateDto) {
        Genre genre = genreRepository.findGenreByName(bookCreateDto.getGenre()).orElseThrow();
        return Book.builder()
                .name(bookCreateDto.getName())
                .genre(genre)
                .build();
    }

    @Override
    public BookDto updateBook(BookUpdateDto bookUpdateDto) {
        Book book = bookRepository.findById(bookUpdateDto.getId()).orElseThrow();
        book.setName(bookUpdateDto.getName());
        Genre genre = genreRepository.findGenreByName(bookUpdateDto.getGenre()).orElseThrow();
        book.setGenre(genre);
        Book savedBook = bookRepository.save(book);
        BookDto bookDto = convertToDto(savedBook);
        return bookDto;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
}