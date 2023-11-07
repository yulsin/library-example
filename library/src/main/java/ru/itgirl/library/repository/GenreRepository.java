package ru.itgirl.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.library.model.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
