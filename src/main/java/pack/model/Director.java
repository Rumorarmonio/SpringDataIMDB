package pack.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data // = @Getter + @Setter + @ToString + @RequiredArgsConstructor + @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity //даёт понять спрингу, что это сущность, которую нужно сохранять в базе данных
@Table(name = "directors") /*Указывает гибернейту, с какой именно таблицей необходимо связать (map) данный класс*/
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*стратегия генерации первичного ключа, автоинкремент, автогенерация*/
    private Integer id;

    //имя и фамилия режиссёра
    @Column(name = "first_name") /*определяет, к какому столбцу в таблице БД относится конкретное поле класса (аттрибут класса)*/
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ElementCollection(fetch = FetchType.EAGER) //отношение "один ко многим", genres - вложенный
    @JoinTable(name = "directors_genres")
    private List<DirectorsGenre> directorsGenres;

    @ManyToMany(fetch = FetchType.EAGER) //отношение "многие ко многим"
    @JoinTable(name = "movies_directors", joinColumns = @JoinColumn(name = "director_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies;
}