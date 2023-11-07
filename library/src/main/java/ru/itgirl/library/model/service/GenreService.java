package ru.itgirl.library.model.service;

import ru.itgirl.library.model.dto.GenreDto;

public interface GenreService {
    GenreDto getGenreById(Long id);
}

