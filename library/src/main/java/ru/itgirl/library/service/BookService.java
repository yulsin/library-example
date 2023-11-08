package ru.itgirl.library.service;

import ru.itgirl.library.model.dto.BookDto;

public interface BookService {
    BookDto getBookById(Long id);
    BookDto getByNameV1(String name);

    BookDto getByNameV2(String name);

    BookDto getByNameV3(String name);
}