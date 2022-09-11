package com.example.demo.dto;

import com.example.demo.model.Student;
import com.example.demo.model.StudentCursKey;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CatalogDto {
    Long student_id;
    Long curs_id;
    int nota;
}
