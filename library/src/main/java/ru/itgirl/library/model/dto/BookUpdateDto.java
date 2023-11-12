package ru.itgirl.library.model.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itgirl.library.model.entity.Genre;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookUpdateDto {
    private Long id;
    private String name;
    private Genre genre;
}