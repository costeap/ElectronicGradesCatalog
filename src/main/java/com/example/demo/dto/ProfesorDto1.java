package com.example.demo.dto;

import com.example.demo.Validators.Password;
import com.example.demo.model.Curs;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
public class ProfesorDto1 {
    private Long id;

    @Length(min=13, max=13)
    private String cnp;

    @Size(min = 5, max = 200, message="Name must be between 5 and 200 characters long")
    private String nume;

    @Email(message = "Email address has invalid format",
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$" )
    private String email;

    @Length(min=10, max=10)
    private String numarTel;

    @Column(unique=true, length=120)
    private String username;

    @Password
    private String parola;
    private Boolean admin;
    private Long curs_id;

    public ProfesorDto1(Long id, String cnp, String nume, String email, String numarTel, String username, String parola, Boolean admin, Long curs_id) {
        this.id=id;
        this.nume = nume;
        this.cnp = cnp;
        this.email = email;
        this.numarTel = numarTel;
        this.username=username;
        this.parola=parola;
        this.admin=admin;
        this.curs_id=curs_id;
    }
}
