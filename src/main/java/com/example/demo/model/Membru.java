package com.example.demo.model;

import com.example.demo.Validators.Password;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Membru {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 5, max = 200, message="Name must be between 5 and 200 characters long")
    private String nume;

    @Length(min=13, max=13)
    private String cnp;

    @Email(message = "Email address has invalid format: ${validatedValue}",
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$" )
    private String email;

    @Column(unique=true, length=120)
    private String username;

    @Password
    private String parola;


    @Override
    public String toString() {
        return "Membru{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", cnp='" + cnp + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }
}
