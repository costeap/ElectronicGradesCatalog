package com.example.demo.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Profesor extends Membru{

    @Length(min=10, max=10)
    private String numarTel;

    private Boolean admin;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE})
    private List<Curs> listaCursuri = new ArrayList<Curs>();

    @Builder
    public Profesor(Long id, String nume, String cnp, String email, String username, String parola, String numarTel, List<Curs> listaCursuri, Boolean admin) {
        super(id,nume, cnp, email, username, parola);
        this.numarTel = numarTel;
        this.listaCursuri=listaCursuri;
        this.admin=admin;
    }
}
