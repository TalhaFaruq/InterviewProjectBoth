package com.interview.coursestudent.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.Set;

@Data
public class CourseDTO {
    private Long id;
    private String courseName;
    private Boolean deleted = Boolean.FALSE;
    @JsonIgnore
    private Set<StudentDTO> students;
}
