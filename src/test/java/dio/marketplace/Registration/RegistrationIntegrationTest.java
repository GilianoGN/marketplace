package dio.marketplace.Registration;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import dio.marketplace.MarketplaceApplication;

@SpringBootTest(classes = MarketplaceApplication.class)
public class RegistrationIntegrationTest {

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> "jdbc:mysql://localhost:3306/registration");
        registry.add("spring.datasource.username", () -> "app");
        registry.add("spring.datasource.password", () -> "app");
    }

    @Test
    public void deveConectarAoBandoDeDados() {
        assertNotNull("O contexto do Spring deveria carregar", this);
    }
}