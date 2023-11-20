package ru.itgirl.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.library.model.entity.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findGenreByName(String name);
}
