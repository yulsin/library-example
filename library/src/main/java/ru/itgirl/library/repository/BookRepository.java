package ru.itgirl.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.library.model.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
