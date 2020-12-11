package pack.model;

import lombok.*;

@Data // = @Getter + @Setter + @ToString + @RequiredArgsConstructor + @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DirectorIdNameProb {
    private Integer id;
    private String name;
    private Float prob;

    public DirectorIdNameProb(Integer identifier, String firstName, String lastName, Float p) {
        id = identifier;
        name = String.format("%s %s", firstName, lastName);
        prob = p;
    }
}
