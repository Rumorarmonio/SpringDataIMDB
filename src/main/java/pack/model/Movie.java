package pack.model;

import lombok.*;

import javax.persistence.*;

@Data // = @Getter + @Setter + @ToString + @RequiredArgsConstructor + @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer year;

    @Column
    private Float rank;
}
