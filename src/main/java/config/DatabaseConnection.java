package config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.sql.*;
@Configuration
public class DatabaseConnection {
    @Value("${PG_URL}")
    private String url;
    @Value("${PG_USERNAME}")
    private String username;
    @Value("${PG_PASSWORD}")
    private String password;
    @Bean
    public Connection getConnection() throws SQLException{
            return DriverManager.getConnection(url,username,password);
    }
}
