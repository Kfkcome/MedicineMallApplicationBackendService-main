package pers.ervinse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("pers.ervinse.mapper")
public class MedicinesMallApplicationBackendServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicinesMallApplicationBackendServiceApplication.class, args);
    }

}
