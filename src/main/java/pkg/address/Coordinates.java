package pkg.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {
    private String name;
    private String x;
    private String y;

    @Override
    public String toString() {
        return "(" +
                "\"" + name + '\"' +
                ", " +  x +
                ", " + y +
                ')';
    }
}
