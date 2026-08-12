package bitlab.kz.New.Controller;

import bitlab.kz.New.Car;
import bitlab.kz.New.Service.CarService;
import bitlab.kz.New.Service.Country;
import bitlab.kz.New.Service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final CountryService countryService;


    @GetMapping("/")
    public String getCars(
            Model model,
            @RequestParam(
                    required = false,
                    name = "minimal_engine_volume"
            ) Double mev) {

        if (mev == null) {
            model.addAttribute("cars", carService.getAll());
        } else {
            model.addAttribute(
                    "cars",
                    carService.getCarByMinimalEngine(mev)
            );
        }

        model.addAttribute(
                "countries",
                countryService.getAll()
        );

        return "index";
    }



    @GetMapping("/{id}")
    public String getById(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "car",
                carService.getById(id)
        );

        model.addAttribute(
                "countries",
                countryService.getAll()
        );

        return "car";
    }


    // =========================
    // ADD CAR
    // =========================

    @PostMapping("/add-car")
    public String addCar(
            @RequestParam(name = "brand") String brand,
            @RequestParam(name = "model") String model,
            @RequestParam(name = "engine_volume") double engineVolume,
            @RequestParam(name = "max_speed") int maxSpeed,
            @RequestParam(name = "country_id") Long countryId) {

        Country country = countryService.getById(countryId);

        Car car = new Car(
                null,
                brand,
                model,
                engineVolume,
                maxSpeed,
                country
        );

        carService.addCar(car);

        return "redirect:/";
    }



    @PostMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            @RequestParam(name = "brand") String brand,
            @RequestParam(name = "model") String model,
            @RequestParam(name = "engine_volume") double engineVolume,
            @RequestParam(name = "max_speed") int maxSpeed,
            @RequestParam(name = "country_id") Long countryId) {

        Country country = countryService.getById(countryId);

        Car car = new Car(
                id,
                brand,
                model,
                engineVolume,
                maxSpeed,
                country
        );

        carService.updateCar(car);

        return "redirect:/";
    }



    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id) {

        carService.deleteCar(id);

        return "redirect:/";
    }



    @GetMapping("/countries")
    public String getCountries(Model model) {

        model.addAttribute(
                "countries",
                countryService.getAll()
        );

        return "countries";
    }


    @PostMapping("/countries/add")
    public String addCountry(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "code") String code) {

        Country country = Country.builder()
                .name(name)
                .code(code)
                .build();

        countryService.addCountry(country);

        return "redirect:/countries";
    }
}