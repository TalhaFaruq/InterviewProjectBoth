package com.interview.coursestudent.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class StudentDTO {
    private Long id;
    private String studentName;
    private String phoneNumber;
    private Boolean deleted=Boolean.FALSE;
    private Set<CourseDTO> courses;

}
