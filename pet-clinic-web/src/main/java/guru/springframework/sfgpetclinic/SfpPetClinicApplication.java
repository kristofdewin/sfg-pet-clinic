package guru.springframework.sfgpetclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

//exclude due to bug in vaadin
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class SfpPetClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SfpPetClinicApplication.class, args);
    }

}
