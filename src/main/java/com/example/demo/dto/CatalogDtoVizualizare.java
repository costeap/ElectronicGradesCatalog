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
public class CatalogDtoVizualizare {
    Long student_id;
    String numeStudent;
    Long curs_id;
    String numeCurs;
    int nota;
}
