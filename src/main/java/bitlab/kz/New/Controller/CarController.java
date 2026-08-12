package bitlab.kz.New.Controller;


import bitlab.kz.New.Car;
import bitlab.kz.New.Service.CarService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    @GetMapping("/")
    public String getCar(){
        return "index";
    }
    @PostMapping("/add-car")
    public String addCar(@RequestParam(name = "barnd") String brand,
                         @RequestParam(name = "model")String model,
                         @RequestParam(name="engine_volume")double engine_volume,
                         @RequestParam(name = "Max_speed")int max_speed){
        carService.addCar(new Car(null,brand,model,engine_volume,max_speed));
        return "redicate:/";
    }
}
