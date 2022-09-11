package com.example.demo.dto;

import com.example.demo.Validators.Password;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembruDto {
    private String username;

    @Password
    private String parola;
}
