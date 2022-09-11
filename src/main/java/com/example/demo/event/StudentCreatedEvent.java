package com.example.demo.event;

import com.example.demo.dto.StudentDto;
import lombok.Data;

@Data
public class StudentCreatedEvent extends BaseEvent{
    private final StudentDto studentDto;
    public StudentCreatedEvent(StudentDto studentDto){
        super(EventType.STUDENT_CREATED);
        this.studentDto=studentDto;
    }

}
