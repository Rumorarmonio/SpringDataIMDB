package pack.model;

import lombok.*;

import javax.persistence.*;

@Data // = @Getter + @Setter + @ToString + @RequiredArgsConstructor + @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DirectorsGenre {
    @Column
    private String genre;

    @Column
    private Float prob;
}