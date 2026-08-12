package bitlab.kz.New;

import bitlab.kz.New.Service.Country;
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
    private String model;
    private String brand;
    private double engineVolume;
    private int maxSpeed;
    private Country country;
}