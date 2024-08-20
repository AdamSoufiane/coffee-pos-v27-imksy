package ai.shreds.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import java.util.Map;

@Configuration
public class InfrastructureLocalContainerEntityManagerFactoryBean extends LocalContainerEntityManagerFactoryBean {

    private DataSource dataSource;

    /**
     * Creates and configures the DataSource bean.
     * @return the configured DataSource
     */
    @Bean
    public DataSource createDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/transactiondb")
                .username("root")
                .password("password")
                .build();
    }

    @Override
    protected void configurePersistenceUnitProperties(Map<String, Object> properties) {
        super.configurePersistenceUnitProperties(properties);
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
    }

    @Override
    void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        super.setDataSource(dataSource);
    }

    @Override
    void setJpaVendorAdapter(HibernateJpaVendorAdapter jpaVendorAdapter) {
        super.setJpaVendorAdapter(jpaVendorAdapter);
    }

    @Override
    void setPackagesToScan(String... packagesToScan) {
        super.setPackagesToScan(packagesToScan);
    }

    /**
     * Sets up and returns the PlatformTransactionManager.
     * @return the configured PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager setupTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(this.getObject());
        return transactionManager;
    }
}