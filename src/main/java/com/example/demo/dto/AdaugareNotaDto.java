package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdaugareNotaDto {
    private Long student_id;
    private Long curs_id;
    private int nota;
    private String numeProfesor;
    private String cnpProfesor;
}
