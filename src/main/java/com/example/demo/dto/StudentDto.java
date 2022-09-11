package com.example.demo.dto;

import com.example.demo.Validators.Password;
import com.example.demo.model.Curs;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
public class StudentDto {
    private Long id;
    @Length(min=13, max=13)

    private String cnp;

    @Size(min = 5, max = 200, message="Name must be between 5 and 200 characters long")
    private String nume;

    @Email(message = "Email address has invalid format",
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$" )
    private String email;

    @Min(1)
    @Max(4)
    private String anStudiu;

    @Column(unique=true, length=120)
    private String username;

    @Password
    private String parola;

    public StudentDto(Long id, String cnp,String nume,String email, String anStudiu, String username, String parola) {
        this.id=id;
        this.cnp = cnp;
        this.nume = nume;
        this.email = email;
        this.anStudiu = anStudiu;
        this.username=username;
        this.parola=parola;
    }
}
