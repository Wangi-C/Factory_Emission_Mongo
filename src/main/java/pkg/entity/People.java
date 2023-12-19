package pkg.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("people")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class People {
    @Id
    private String _id;
    private int age;
    private String name;
    private int salary;
}
