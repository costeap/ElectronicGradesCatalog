package com.example.demo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentCursKey implements Serializable {

    @Column(name = "student_id")
    Long studentId;

    @Column(name = "curs_id")
    Long cursId;

    public StudentCursKey(Long studentId, Long cursId) {
        this.studentId = studentId;
        this.cursId = cursId;
    }

    public StudentCursKey() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCursKey that = (StudentCursKey) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(cursId, that.cursId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, cursId);
    }
}

