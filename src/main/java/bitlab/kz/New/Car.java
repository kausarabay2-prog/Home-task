package bitlab.kz.New;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private Long id;
    private  String name;
    private String model;
    private String brand;
    private double engin_value;
    private int maxSpeed;

    public Car(Object o, String brand, String model, double engineVolume, int maxSpeed) {
    }
}
