package com.example.demo;
import com.example.demo.service.CursService;
import com.example.demo.service.MembruService;
import com.example.demo.service.ProfesorService;
import com.example.demo.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.Arrays;
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableScheduling
public class StudentApplication {
    @Autowired
    private static StudentService studentService;
    @Autowired
    private static ProfesorService profesorService;
    @Autowired
    private static CursService cursService;
    @Autowired
    private static MembruService membruService;
    public static void main(String[] args) {
        ModelMapper mapper=new ModelMapper();
        SpringApplication.run(StudentApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
