package com.example.demo.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;

    private String actiune;
    private Timestamp timestamp;
}
