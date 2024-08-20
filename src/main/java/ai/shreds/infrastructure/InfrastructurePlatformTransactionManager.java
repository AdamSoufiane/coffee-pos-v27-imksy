package ai.shreds.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration class for setting up the PlatformTransactionManager.
 */
@Configuration
@RequiredArgsConstructor
public class InfrastructurePlatformTransactionManager {

    private static final Logger log = LoggerFactory.getLogger(InfrastructurePlatformTransactionManager.class);
    private final DataSource dataSource;
    private final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    /**
     * Configures and returns the PlatformTransactionManager bean.
     *
     * @param entityManagerFactory the EntityManagerFactory
     * @return the configured PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        log.info("Configuring JpaTransactionManager");
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    /**
     * Returns the EntityManagerFactory bean.
     *
     * @return the EntityManagerFactory
     */
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        try {
            log.info("Creating EntityManagerFactory");
            return entityManagerFactoryBean.getObject();
        } catch (Exception e) {
            log.error("Error creating EntityManagerFactory", e);
            throw new RuntimeException("Failed to create EntityManagerFactory", e);
        }
    }
}