package ru.itgirl.library.model.service;

import ru.itgirl.library.model.dto.BookDto;

public interface BookService {
    BookDto getBookById(Long id);
}
