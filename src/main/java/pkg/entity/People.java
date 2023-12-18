package pkg.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("people")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class People {
    @Id
    private String _id;
    private int age;
    private String name;
    private int salary;
}
