package ai.shreds.infrastructure;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

@Configuration
public class InfrastructureDataSource {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureDataSource.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriverClassName;

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbDriverClassName() {
        return dbDriverClassName;
    }

    public void setDbDriverClassName(String dbDriverClassName) {
        this.dbDriverClassName = dbDriverClassName;
    }

    @Bean
    public DataSource dataSource() {
        if (dbUrl == null || dbUrl.isEmpty()) {
            logger.error("Database URL must not be null or empty");
            throw new IllegalArgumentException("Database URL must not be null or empty");
        }
        if (dbUsername == null || dbUsername.isEmpty()) {
            logger.error("Database username must not be null or empty");
            throw new IllegalArgumentException("Database username must not be null or empty");
        }
        if (dbPassword == null || dbPassword.isEmpty()) {
            logger.error("Database password must not be null or empty");
            throw new IllegalArgumentException("Database password must not be null or empty");
        }
        if (dbDriverClassName == null || dbDriverClassName.isEmpty()) {
            logger.error("Database driver class name must not be null or empty");
            throw new IllegalArgumentException("Database driver class name must not be null or empty");
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUsername);
        config.setPassword(dbPassword);
        config.setDriverClassName(dbDriverClassName);
        logger.info("Configuring HikariDataSource with URL: {}", dbUrl);
        return new HikariDataSource(config);
    }
}