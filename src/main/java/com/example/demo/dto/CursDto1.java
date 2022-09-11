package com.example.demo.dto;

import com.example.demo.model.Profesor;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
public class CursDto1 {
    private Long id;

    @Size(min = 3, max = 200, message="The course name must be between 3 and 200 characters long")
    private String numeCurs;

    @Min(1)
    @Max(4)
    private int anDeStudiu;
    private Long profesor_id;

    public CursDto1(Long id, String numeCurs, int anDeStudiu, Long profesor_id) {
        this.id=id;
        this.numeCurs = numeCurs;
        this.anDeStudiu = anDeStudiu;
        this.profesor_id=profesor_id;
    }
}
