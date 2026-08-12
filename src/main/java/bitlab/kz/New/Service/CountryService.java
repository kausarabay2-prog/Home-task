package bitlab.kz.New.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CountryService {

    private final Connection connection;


    public List<Country> getAll() {

        List<Country> countries = new ArrayList<>();

        try {

            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT id, name, code FROM t_countries;"
                    );

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Country country = Country.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .code(rs.getString("code"))
                        .build();

                countries.add(country);
            }

            rs.close();
            statement.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return countries;
    }


    public Country getById(Long id) {

        Country country = new Country();

        try {

            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT id, name, code FROM t_countries WHERE id = ?;"
                    );

            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                country.setId(rs.getLong("id"));
                country.setName(rs.getString("name"));
                country.setCode(rs.getString("code"));
            }

            rs.close();
            statement.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return country;
    }



    public boolean addCountry(Country c) {

        try {

            PreparedStatement statement =
                    connection.prepareStatement(
                            "INSERT INTO t_countries (name, code) VALUES (?, ?);"
                    );

            statement.setString(1, c.getName());
            statement.setString(2, c.getCode());

            statement.executeUpdate();

            statement.close();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}