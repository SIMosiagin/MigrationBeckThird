package uplaodEmployee;

import uplaodEmployee.controller.RestConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class })
public class NCESThird {
    public static void main(String[] args) {
        SpringApplication.run(NCESThird.class, args);
    }
}
