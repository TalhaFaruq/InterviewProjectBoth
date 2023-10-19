package com.interview.coursestudent.mapper;

import com.interview.coursestudent.DTO.CourseDTO;
import com.interview.coursestudent.Entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ICourseMapper {
    ICourseMapper INSTANCE = Mappers.getMapper(ICourseMapper.class);

    @Mapping(target = "students", ignore = true)
    CourseDTO courseToCourseDTO(Course course);

    @Mapping(target = "students", ignore = true)
    Course courseDTOToCourse(CourseDTO courseDTO);
}
