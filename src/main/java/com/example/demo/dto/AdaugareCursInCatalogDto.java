package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdaugareCursInCatalogDto {
    private Long student_id;
    private Long curs_id;
    private int nota;
}
