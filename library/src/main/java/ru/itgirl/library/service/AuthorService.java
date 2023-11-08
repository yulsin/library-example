package ru.itgirl.library.service;

import ru.itgirl.library.model.dto.AuthorDto;
import ru.itgirl.library.model.dto.BookDto;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);

    AuthorDto getBySurnameV1(String surname);

    AuthorDto getBySurnameV2(String surname);

    AuthorDto getBySurnameV3(String surname);
}
