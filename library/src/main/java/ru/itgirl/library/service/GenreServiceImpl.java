package ru.itgirl.library.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.library.model.dto.AuthorDto;
import ru.itgirl.library.model.dto.BookDto;
import ru.itgirl.library.model.dto.GenreDto;
import ru.itgirl.library.model.entity.Genre;
import ru.itgirl.library.repository.GenreRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public GenreDto getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        return convertToDto(genre);
    }

    private GenreDto convertToDto(Genre genre) {
        List<BookDto> bookDtoList = genre.getBooks()
                    .stream()
                    .map(book -> BookDto.builder()
                            .authors(book.getAuthors()
                                    .stream()
                                    .map(author -> AuthorDto.builder()
                                            .id(author.getId())
                                            .name(author.getName())
                                            .surname(author.getSurname())
                                            .build()).toList())
                            .name(book.getName())
                            .id(book.getId())
                            .build()
                    ).toList();
        return GenreDto.builder()
                .books(bookDtoList)
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
