package ru.itgirl.library.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itgirl.library.model.dto.AuthorCreateDto;
import ru.itgirl.library.model.dto.AuthorDto;
import ru.itgirl.library.model.dto.AuthorUpdateDto;
import ru.itgirl.library.model.dto.BookDto;
import ru.itgirl.library.model.entity.Author;
import ru.itgirl.library.repository.AuthorRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public AuthorDto getBySurnameV1(String surname) {
        Author author = authorRepository.findAuthorBySurname(surname).orElseThrow();
        return convertToDto(author);
    }

    @Override
    public AuthorDto getBySurnameV2(String surname) {
        Author author = authorRepository.findAuthorBySurnameBySql(surname).orElseThrow();
        return convertToDto(author);
    }

    @Override
    public AuthorDto getBySurnameV3(String surname) {
        Specification<Author> specification = Specification.where(new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get("surname"), surname);
            }
        });

        Author author = authorRepository.findOne(specification).orElseThrow();
        return convertToDto(author);
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        return convertToDto(author);
    }

    private AuthorDto convertToDto(Author author) {
        List<BookDto> bookDtoList = null;
        if(author.getBooks() != null) {
            bookDtoList = author.getBooks()
                    .stream()
                    .map(book -> BookDto.builder()
                            .genre(book.getGenre().getName())
                            .name(book.getName())
                            .id(book.getId())
                            .build()
                    ).toList();
        }
        return AuthorDto.builder()
                .books(bookDtoList)
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

    @Override
    public AuthorDto createAuthor(AuthorCreateDto authorCreateDto) {
        Author author = authorRepository.save(convertDtoToEntity(authorCreateDto));
        AuthorDto authorDto = convertToDto(author);
        return authorDto;
    }

    private Author convertDtoToEntity(AuthorCreateDto authorCreateDto) {
        return Author.builder()
                .name(authorCreateDto.getName())
                .surname(authorCreateDto.getSurname())
                .build();
    }

    @Override
    public AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto) {
        Author author = authorRepository.findById(authorUpdateDto.getId()).orElseThrow();
        author.setName(authorUpdateDto.getName());
        author.setSurname(authorUpdateDto.getSurname());
        Author savedAuthor = authorRepository.save(author);
        AuthorDto authorDto = convertToDto(savedAuthor);
        return authorDto;
    }

    @Override
        public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}