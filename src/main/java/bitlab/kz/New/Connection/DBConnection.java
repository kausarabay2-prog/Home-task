package bitlab.kz.New.Connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
public class DBConnection {

    private Connection connection;


    @Bean
    public Connection connection() {
        return connection;
    }


    public DBConnection() {

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "postgres"
            );

            System.out.println(
                    "Database connected successfully!"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
