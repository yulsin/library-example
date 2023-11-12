package ru.itgirl.library.model.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itgirl.library.model.entity.Genre;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookCreateDto {
    private String name;
    private Genre genre;
}
