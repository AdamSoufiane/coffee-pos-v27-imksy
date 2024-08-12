package ai.shreds.infrastructure;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import lombok.Getter;
import lombok.Setter;

@Configuration
public class InfrastructureDataSource {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureDataSource.class);

    @Getter @Setter @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Getter @Setter @Value("${spring.datasource.username}")
    private String username;

    @Getter @Setter @Value("${spring.datasource.password}")
    private String password;

    @Getter @Setter @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        validateDataSourceProperties();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("maximumPoolSize", "10");
        config.addDataSourceProperty("connectionTimeout", "30000");
        logger.info("Configuring DataSource with URL: {}", jdbcUrl);
        return new HikariDataSource(config);
    }

    private void validateDataSourceProperties() {
        if (jdbcUrl == null || jdbcUrl.isEmpty()) {
            throw new IllegalArgumentException("JDBC URL must not be null or empty");
        }
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username must not be null or empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password must not be null or empty");
        }
        if (driverClassName == null || driverClassName.isEmpty()) {
            throw new IllegalArgumentException("Driver Class Name must not be null or empty");
        }
    }
}