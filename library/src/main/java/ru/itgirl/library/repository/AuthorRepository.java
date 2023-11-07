package ru.itgirl.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.library.model.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
