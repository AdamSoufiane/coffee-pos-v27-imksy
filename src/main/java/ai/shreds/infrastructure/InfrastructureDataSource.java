package ai.shreds.infrastructure;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Configuration
@Data
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class InfrastructureDataSource {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureDataSource.class);

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
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