package com.example.demo.model;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Curs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 200, message="The course name must be between 3 and 200 characters long")
    private String numeCurs;

    @Min(1)
    @Max(4)
    private int anDeStudiu;


    @ManyToMany(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
    private List<Profesor> listaProfesori = new ArrayList<Profesor>();

    public Curs(String numeCurs, int anDeStudiu, List<Profesor> listaProfesori) {
        this.numeCurs = numeCurs;
        this.anDeStudiu = anDeStudiu;
        this.listaProfesori = listaProfesori;
    }

    @Override
    public String toString() {
        return "Curs{" +
                "id=" + id +
                ", numeCurs='" + numeCurs + '\'' +
                ", anDeStudiu=" + anDeStudiu +
                ", listaProfesori=" + listaProfesori +
                '}';
    }
}
