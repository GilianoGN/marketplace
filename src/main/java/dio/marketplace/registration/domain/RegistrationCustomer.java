package dio.marketplace.registration.domain;

import org.springframework.util.Assert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationCustomer {
    private RegistrationCustomerId id;
    private String name;
    private String email;

    public RegistrationCustomer(RegistrationCustomerId id, String name, String email) {
        Assert.notNull(name, "Name must not be null");
        Assert.notNull(email, "Email must not be null");

        this.id = id;
        this.name = name;
        this.email = email;
    }

    public RegistrationCustomer(String name, String email) {
        this(new RegistrationCustomerId(), name, email);
    }
}
