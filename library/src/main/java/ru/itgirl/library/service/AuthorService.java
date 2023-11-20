package ru.itgirl.library.service;

import ru.itgirl.library.model.dto.AuthorCreateDto;
import ru.itgirl.library.model.dto.AuthorDto;
import ru.itgirl.library.model.dto.AuthorUpdateDto;
import ru.itgirl.library.model.dto.BookDto;

import java.util.List;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);

    AuthorDto getBySurnameV1(String surname);

    AuthorDto getBySurnameV2(String surname);

    AuthorDto getBySurnameV3(String surname);

    AuthorDto createAuthor(AuthorCreateDto authorCreateDto);

    AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto);

    void deleteAuthor(Long id);

    List<AuthorDto> getAllAuthors();
}
