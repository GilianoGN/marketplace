package dio.marketplace.ticketing.infrastructure;

//import java.util.LinkedHashMap;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
//import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
//import org.springframework.boot.jpa.autoconfigure.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.lang.NonNull;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(
    basePackages = "dio.marketplace.ticketing.infrastructure.persistence.repository",
    entityManagerFactoryRef = "ticketingEntityManagerFactory",
    transactionManagerRef = "ticketingTransactionManager")
@EnableRedisRepositories(
    basePackages = "dio.marketplace.ticketing.infrastructure.persistence.repository",
    redisTemplateRef = "ticketingRedisTemplate")
public class TicketingConfiguration {
    
    @Qualifier("ticketing")
    @Bean(defaultCandidate = false)
    @ConfigurationProperties("ticketing.datasource")
    public DataSourceProperties ticketingDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Qualifier("ticketing")
    @Bean(defaultCandidate = false)
    @ConfigurationProperties("ticketing.datasource.configuration")
    public HikariDataSource ticketingDataSource(@Qualifier("ticketing") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Qualifier("ticketing")
    @Bean(defaultCandidate = false)
    @ConfigurationProperties("ticketing.jpa")
    public JpaProperties ticketingJpaProperties() {
        return new JpaProperties();
    }

    @Qualifier("ticketing")
    @Bean
    public LocalContainerEntityManagerFactoryBean ticketingEntityManagerFactory(
            // 1. O Spring injeta o builder automaticamente aqui
            EntityManagerFactoryBuilder builder,
            @Qualifier("ticketing") DataSource dataSource,
            @Qualifier("ticketing") JpaProperties jpaProperties) {

        // 2. Use o 'builder' injetado diretamente
        return builder
            .dataSource(dataSource)
            .packages("dio.marketplace.ticketing")
            .persistenceUnit("ticketing")
            .properties(jpaProperties.getProperties()) // Importante manter se houver propriedades extras
            .build();
    }

    @SuppressWarnings("null")
    @Qualifier("ticketing")
    @Bean
    public PlatformTransactionManager ticketingTransactionManager(
        @Qualifier("ticketing") LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }

    @Qualifier("ticketing")
    @Bean(defaultCandidate = false)
    public RedisConnectionFactory ticketingRedisConnectionFactory(
            @Value("${ticketing.redis.host}") @NonNull String hostName,
            @Value("${ticketing.redis.port}") int port) {
        return new JedisConnectionFactory(new RedisStandaloneConfiguration(hostName, port));
    }

    @Qualifier("ticketing")
    @Bean(defaultCandidate = false)
    public RedisTemplate<?, ?> ticketingRedisTemplate(@Qualifier("ticketing") RedisConnectionFactory connectionFactory) {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    @Qualifier("ticketing")
    @Bean(name = "ticketingStringRedisTemplate", defaultCandidate = false)
    public StringRedisTemplate ticketingStringRedisTemplate(@Qualifier("ticketing") @NonNull RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}