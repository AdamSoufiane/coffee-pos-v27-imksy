package ai.shreds.infrastructure;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Configuration class for setting up the database connection, EntityManagerFactory, and TransactionManager.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ai.shreds.infrastructure")
public class InfrastructureDatabaseConfig {

    /**
     * URL of the database.
     */
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    /**
     * Username for the database connection.
     */
    @Value("${spring.datasource.username}")
    private String databaseUsername;

    /**
     * Password for the database connection.
     */
    @Value("${spring.datasource.password}")
    private String databasePassword;

    /**
     * Driver class name for the database connection.
     */
    @Value("${spring.datasource.driver-class-name}")
    private String databaseDriverClassName;

    /**
     * Hibernate dialect for the database.
     */
    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;

    /**
     * Configures the DataSource bean.
     *
     * @return the configured DataSource
     */
    @Bean
    public DataSource dataSource() {
        try {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(databaseUrl);
            hikariConfig.setUsername(databaseUsername);
            hikariConfig.setPassword(databasePassword);
            hikariConfig.setDriverClassName(databaseDriverClassName);
            return new HikariDataSource(hikariConfig);
        } catch (Exception e) {
            throw new RuntimeException("Failed to configure DataSource", e);
        }
    }

    /**
     * Configures the EntityManagerFactory bean.
     *
     * @return the configured LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        try {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource());
            em.setPackagesToScan("ai.shreds.domain");
            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            em.getJpaPropertyMap().put("hibernate.dialect", hibernateDialect);
            return em;
        } catch (Exception e) {
            throw new RuntimeException("Failed to configure EntityManagerFactory", e);
        }
    }

    /**
     * Configures the TransactionManager bean.
     *
     * @return the configured PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        try {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
            return transactionManager;
        } catch (Exception e) {
            throw new RuntimeException("Failed to configure TransactionManager", e);
        }
    }
}