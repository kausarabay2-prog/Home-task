package bitlab.kz.New.Service;

import bitlab.kz.New.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CarService {

    private final Connection connection;

    public boolean addCar(Car car) {

        try {

            PreparedStatement statement =
                    connection.prepareStatement(
                            "INSERT INTO t_cars " +
                                    "(brand, model, engine_volume, max_speed, country_id) " +
                                    "VALUES (?, ?, ?, ?, ?)"
                    );

            statement.setString(1, car.getBrand());
            statement.setString(2, car.getModel());
            statement.setDouble(3, car.getEngineVolume());
            statement.setInt(4, car.getMaxSpeed());
            statement.setLong(5, car.getCountry().getId());

            statement.executeUpdate();

            statement.close();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }




    public List<Car> getAll() {

        List<Car> cars = new ArrayList<>();

        try {

            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT " +
                                    "c.id, " +
                                    "c.brand, " +
                                    "c.model, " +
                                    "c.engine_volume, " +
                                    "c.max_speed, " +
                                    "c.country_id, " +
                                    "co.name AS country_name, " +
                                    "co.code AS country_code " +
                                    "FROM t_cars c " +
                                    "LEFT JOIN t_countries co " +
                                    "ON c.country_id = co.id"
                    );

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Country country = Country.builder()
                        .id(rs.getLong("country_id"))
                        .name(rs.getString("country_name"))
                        .code(rs.getString("country_code"))
                        .build();

                Car car = Car.builder()
                        .id(rs.getLong("id"))
                        .brand(rs.getString("brand"))
                        .model(rs.getString("model"))
                        .engineVolume(
                                rs.getDouble("engine_volume")
                        )
                        .maxSpeed(
                                rs.getInt("max_speed")
                        )
                        .country(country)
                        .build();

                cars.add(car);
            }

            rs.close();
            statement.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return cars;
    }


    public Car getById(Long id) {

        Car car = new Car();

        try {

            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT " +
                                    "c.id AS car_id, " +
                                    "c.brand, " +
                                    "c.model, " +
                                    "c.engine_volume, " +
                                    "c.max_speed, " +
                                    "c.country_id, " +
                                    "co.name AS country_name, " +
                                    "co.code AS country_code " +
                                    "FROM t_cars c " +
                                    "LEFT JOIN t_countries co " +
                                    "ON c.country_id = co.id " +
                                    "WHERE c.id = ?"
                    );

            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                car.setId(
                        rs.getLong("car_id")
                );

                car.setBrand(
                        rs.getString("brand")
                );

                car.setModel(
                        rs.getString("model")
                );

                car.setEngineVolume(
                        rs.getDouble("engine_volume")
                );

                car.setMaxSpeed(
                        rs.getInt("max_speed")
                );

                Country country = new Country(
                        rs.getLong("country_id"),
                        rs.getString("country_name"),
                        rs.getString("country_code")
                );

                car.setCountry(country);
            }

            rs.close();
            statement.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return car;
    }


    public List<Car> getCarByMinimalEngine(
            double minimalEngineVolume) {

        List<Car> cars = new ArrayList<>();

        try {

            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT " +
                                    "c.id, " +
                                    "c.brand, " +
                                    "c.model, " +
                                    "c.engine_volume, " +
                                    "c.max_speed, " +
                                    "c.country_id, " +
                                    "co.name AS country_name, " +
                                    "co.code AS country_code " +
                                    "FROM t_cars c " +
                                    "LEFT JOIN t_countries co " +
                                    "ON c.country_id = co.id " +
                                    "WHERE c.engine_volume >= ?"
                    );

            statement.setDouble(
                    1,
                    minimalEngineVolume
            );

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Country country = new Country(
                        rs.getLong("country_id"),
                        rs.getString("country_name"),
                        rs.getString("country_code")
                );

                Car car = Car.builder()
                        .id(rs.getLong("id"))
                        .brand(rs.getString("brand"))
                        .model(rs.getString("model"))
                        .engineVolume(
                                rs.getDouble("engine_volume")
                        )
                        .maxSpeed(
                                rs.getInt("max_speed")
                        )
                        .country(country)
                        .build();

                cars.add(car);
            }

            rs.close();
            statement.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return cars;
    }


    // =========================
    // UPDATE CAR
    // =========================

    public boolean updateCar(Car car) {

        try {

            PreparedStatement statement =
                    connection.prepareStatement(
                            "UPDATE t_cars " +
                                    "SET brand = ?, " +
                                    "model = ?, " +
                                    "engine_volume = ?, " +
                                    "max_speed = ?, " +
                                    "country_id = ? " +
                                    "WHERE id = ?"
                    );

            statement.setString(
                    1,
                    car.getBrand()
            );

            statement.setString(
                    2,
                    car.getModel()
            );

            statement.setDouble(
                    3,
                    car.getEngineVolume()
            );

            statement.setInt(
                    4,
                    car.getMaxSpeed()
            );

            statement.setLong(
                    5,
                    car.getCountry().getId()
            );

            statement.setLong(
                    6,
                    car.getId()
            );

            statement.executeUpdate();

            statement.close();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }



    public boolean deleteCar(Long id) {

        try {

            PreparedStatement statement =
                    connection.prepareStatement(
                            "DELETE FROM t_cars WHERE id = ?"
                    );

            statement.setLong(1, id);

            statement.executeUpdate();

            statement.close();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}