package dio.marketplace.catalog.infrastructure;

import java.util.LinkedHashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.jpa.autoconfigure.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(
    basePackages = "dio.marketplace.catalog.infrastructure.repository",
    entityManagerFactoryRef = "catalogEntityManagerFactory",
    transactionManagerRef = "catalogTransactionManager")
@EnableMongoRepositories(
    basePackages = "dio.marketplace.catalog.infrastructure.repository")
@EnableMongoAuditing
public class CatalogConfiguration {
    @Qualifier("catalog")
    @Bean
    @ConfigurationProperties("catalog.datasource")
    public DataSourceProperties catalogDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Qualifier("catalog")
    @Bean
    @ConfigurationProperties("catalog.datasource.configuration")
    public HikariDataSource catalogDataSource(@Qualifier("catalog") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Qualifier("catalog")
    @Bean
    @ConfigurationProperties("catalog.jpa")
    public JpaProperties catalogJpaProperties() {
        return new JpaProperties();
    }

    @Qualifier("catalog")
    @Bean
    public LocalContainerEntityManagerFactoryBean catalogEntityManagerFactory(
                @Qualifier("catalog") DataSource dataSource,
                @Qualifier("catalog") JpaProperties jpaProperties) {
        var builder = new EntityManagerFactoryBuilder(
            new HibernateJpaVendorAdapter(),
            x -> new LinkedHashMap<>(jpaProperties.getProperties()),
            null
        );

        return builder
            .dataSource(dataSource)
            .packages("dio.marketplace.catalog")
            .persistenceUnit("catalog")
            .properties(jpaProperties.getProperties())
            .build();
    }

    @Qualifier("catalog")
    @Bean
    public PlatformTransactionManager catalogTransactionManager(
        @Qualifier("catalog") LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }
}