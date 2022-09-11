package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@XmlRootElement(name="catalog")
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalog {

    @EmbeddedId
    StudentCursKey id;

    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    @XmlElement(name="student")
    Student student;

    @ManyToOne
    @MapsId("curs_id")
    @JoinColumn(name = "curs_id")
    @XmlElement(name="curs")
    Curs curs;

    @XmlElement
    int nota;

    public Catalog(Student student, Curs curs, int nota) {
        StudentCursKey studentCursKey=new StudentCursKey(student.getId(), curs.getId());
        this.id=studentCursKey;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return
                "student=" + student.getNume() +
                ", curs=" + curs.getNumeCurs() +
                ", nota=" + nota +
                '}';
    }
}
