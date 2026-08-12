package bitlab.kz.New.Service;


import bitlab.kz.New.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;


@Component
@RequiredArgsConstructor
public class CarService {
    private Connection connection;
    public boolean addCar(Car c ){
        try {
            PreparedStatement smth = connection.prepareStatement("INSERT  INTO  t_cars(id,brand,model, engine_voulume,max_speed) values (NULL,?,?,?,?);"
            );
            smth.setString(1,c.getBrand());
            smth.setString(2,c.getModel());
            smth.setDouble(3,c.getEngin_value());
            smth.setInt(4,c.getMaxSpeed());

            smth.executeUpdate();
            smth.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
