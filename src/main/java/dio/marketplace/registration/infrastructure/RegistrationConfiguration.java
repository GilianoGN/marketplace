package dio.marketplace.registration.infrastructure;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(
    basePackages = "dio.marketplace.registration",
    entityManagerFactoryRef = "registrationEntityManagerFactory",
    transactionManagerRef = "registrationTransactionManager")
public class RegistrationConfiguration {
    @Primary
    @Bean
    @ConfigurationProperties("registration.datasource")
    public DataSourceProperties registrationDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    @ConfigurationProperties("registration.datasource.configuration")
    public HikariDataSource registrationDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean
    @ConfigurationProperties("registration.jpa")
    public JpaProperties registrationJpaProperties() {
        return new JpaProperties();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean registrationEntityManagerFactory(
            EntityManagerFactoryBuilder builder, 
            DataSource dataSource,
            JpaProperties jpaProperties) {

        return builder
                .dataSource(dataSource)
                .packages("dio.marketplace.registration")
                .persistenceUnit("registration")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager registrationTransactionManager(
        LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }
}