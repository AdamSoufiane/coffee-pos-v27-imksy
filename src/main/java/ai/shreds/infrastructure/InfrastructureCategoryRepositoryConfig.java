package ai.shreds.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ai.shreds.domain.DomainCategoryRepositoryPort;

/**
 * Configuration class for Category Repository.
 * This class provides the bean configuration for the InfrastructureCategoryRepositoryImpl
 * which implements the DomainCategoryRepositoryPort interface.
 */
@Configuration
public class InfrastructureCategoryRepositoryConfig {

    /**
     * Configures and returns an instance of InfrastructureCategoryRepositoryImpl.
     *
     * @return an instance of DomainCategoryRepositoryPort
     */
    @Bean
    public DomainCategoryRepositoryPort configureRepository() {
        return new InfrastructureCategoryRepositoryImpl();
    }
}