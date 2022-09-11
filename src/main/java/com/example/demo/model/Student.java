package com.example.demo.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Student extends Membru{

    @Min(1)
    @Max(4)
    private String anStudiu;

    @Builder
    public Student(Long id, String nume, String cnp, String email, String username, String parola, String anStudiu, List<Curs> listaCursuri) {
        super(id, nume, cnp, email, username, parola);
        this.anStudiu = anStudiu;
    }

    @Override
    public String toString() {
        return "Student{" + super.toString()+
                "anStudiu='" + anStudiu + '\'' +
                '}';
    }
}
