package ai.shreds.infrastructure;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import javax.sql.DataSource;
import java.util.Map;

public class InfrastructureLocalContainerEntityManagerFactoryBean extends LocalContainerEntityManagerFactoryBean {

    private DataSource dataSource;

    protected void configurePersistenceUnitProperties(Map<String, Object> properties) {
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        super.setDataSource(dataSource);
    }

    public void setPackagesToScan(String... packagesToScan) {
        super.setPackagesToScan(packagesToScan);
    }

    public void setJpaVendorAdapter(HibernateJpaVendorAdapter jpaVendorAdapter) {
        super.setJpaVendorAdapter(jpaVendorAdapter);
    }
}