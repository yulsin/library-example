package ru.itgirl.library.service;

import ru.itgirl.library.model.dto.*;

import java.util.List;

public interface BookService {
    BookDto getBookById(Long id);
    BookDto getByNameV1(String name);

    BookDto getByNameV2(String name);

    BookDto getByNameV3(String name);

    BookDto createBook(BookCreateDto bookCreateDto);

    BookDto updateBook(BookUpdateDto bookUpdateDto);

    void deleteBook(Long id);

    List<BookDto> getAllBooks();
}