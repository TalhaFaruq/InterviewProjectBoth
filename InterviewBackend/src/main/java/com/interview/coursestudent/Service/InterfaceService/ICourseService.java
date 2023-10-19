package com.interview.coursestudent.Service.InterfaceService;

import com.interview.coursestudent.DTO.CourseDTO;
import com.interview.coursestudent.Entity.Course;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ICourseService {

    CourseDTO mergeCourse(@NotNull CourseDTO courseDTO);

    CourseDTO getById(Long id);

    CourseDTO deleteCourse(Long id);

    List<Course> searchCourse(Pageable pageable, String name, Long id, String sortBy, String sortOrder);

}
