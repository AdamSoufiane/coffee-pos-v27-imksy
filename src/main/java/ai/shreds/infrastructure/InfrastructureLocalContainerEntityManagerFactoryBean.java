package ai.shreds.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration class for setting up LocalContainerEntityManagerFactoryBean.
 */
@Configuration
public class InfrastructureLocalContainerEntityManagerFactoryBean {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureLocalContainerEntityManagerFactoryBean.class);

    @Autowired
    private DataSource dataSource;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${spring.jpa.show-sql}")
    private boolean showSql;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    /**
     * Creates and configures the LocalContainerEntityManagerFactoryBean.
     *
     * @return configured LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        try {
            logger.info("Configuring EntityManagerFactory");
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource);
            em.setPackagesToScan(new String[]{"ai.shreds.domain"});

            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            em.setJpaProperties(additionalProperties());

            return em;
        } catch (Exception e) {
            logger.error("Failed to configure EntityManagerFactory", e);
            throw new RuntimeException("Failed to configure EntityManagerFactory", e);
        }
    }

    /**
     * Configures the transaction manager for JPA.
     *
     * @return configured PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        logger.info("Configuring TransactionManager");
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    /**
     * Additional JPA properties.
     *
     * @return Properties object containing additional JPA properties
     */
    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.show_sql", String.valueOf(showSql));
        return properties;
    }
}