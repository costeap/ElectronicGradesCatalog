package com.example.demo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
public class TrimitereEmailDto {
    private String toEmail;
    private String body;
    private String subject;

    public TrimitereEmailDto(String toEmail, String body, String subject)
    {
        this.toEmail=toEmail;
        this.body=body;
        this.subject=subject;
    }

}
