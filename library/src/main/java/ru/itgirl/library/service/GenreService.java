package ru.itgirl.library.service;

import ru.itgirl.library.model.dto.GenreDto;

public interface GenreService {
    GenreDto getGenreById(Long id);
}

