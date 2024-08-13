package ai.shreds.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class InfrastructureDataSource {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureDataSource.class);

    @Bean
    public DataSource dataSource() {
        String dbUrl = System.getenv("DB_URL");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        if (dbUrl == null || dbUsername == null || dbPassword == null) {
            throw new IllegalArgumentException("Database environment variables are not set");
        }

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        try {
            logger.info("Attempting to get a connection from the data source");
            DataSource dataSource = dataSource();
            Connection connection = dataSource.getConnection();
            logger.info("Successfully obtained a connection from the data source");
            return connection;
        } catch (SQLException e) {
            logger.error("Failed to get a connection from the data source", e);
            throw new SQLException("Failed to get a connection from the data source", e);
        }
    }
}