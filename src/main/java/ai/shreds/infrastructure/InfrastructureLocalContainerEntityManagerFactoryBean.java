package ai.shreds.infrastructure;

import org.hibernate.cfg.AvailableSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableTransactionManagement;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class InfrastructureLocalContainerEntityManagerFactoryBean {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureLocalContainerEntityManagerFactoryBean.class);

    private final DataSource dataSource;

    public InfrastructureLocalContainerEntityManagerFactoryBean(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        try {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource);
            em.setPackagesToScan("ai.shreds.domain");
            em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            em.setJpaProperties(hibernateProperties());
            logger.info("EntityManagerFactory configured successfully.");
            return em;
        } catch (Exception e) {
            logger.error("Error configuring EntityManagerFactory", e);
            throw e;
        }
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty(AvailableSettings.USE_JDBC_METADATA_DEFAULTS, "false");
        return properties;
    }
}