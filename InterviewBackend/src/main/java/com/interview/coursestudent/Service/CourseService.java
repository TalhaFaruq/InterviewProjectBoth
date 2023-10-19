package com.interview.coursestudent.Service;


import com.interview.coursestudent.DTO.CourseDTO;
import com.interview.coursestudent.Entity.Course;
import com.interview.coursestudent.Repository.ICourseRepo;
import com.interview.coursestudent.Service.InterfaceService.ICourseService;
import com.interview.coursestudent.mapper.ICourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService {

    private final ICourseRepo courseRepo;

    @Override
    public CourseDTO mergeCourse(CourseDTO courseDTO) {
        Course entity = ICourseMapper.INSTANCE.courseDTOToCourse(courseDTO);
        entity = courseRepo.save(entity);
        courseDTO = ICourseMapper.INSTANCE.courseToCourseDTO(entity);
        return courseDTO;
    }

    @Override
    public CourseDTO getById(Long id) {
        return ICourseMapper.INSTANCE.courseToCourseDTO(courseRepo.getById(id));
    }

    @Override
    public CourseDTO deleteCourse(Long id) {
        CourseDTO courseDTO = ICourseMapper.INSTANCE.courseToCourseDTO(courseRepo.findById(id).get());
        courseDTO.setDeleted(Boolean.TRUE);
        return ICourseMapper.INSTANCE.courseToCourseDTO(courseRepo.save(ICourseMapper.INSTANCE.courseDTOToCourse(courseDTO)));
    }

    @Override
    public List<Course> searchCourse(Pageable pageable, String name, Long id, String sortBy, String sortOrder) {
        Specification<Course> spec = Specification.where(null);
        if (name != null && !name.isBlank()) {
            spec = spec.and((root, query, builder) -> builder.like(root.get("courseName"), "%" + name + "%"));
        }
        if (id != null) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("id"), id));
        }

        // Apply sorting criteria
        if (sortBy != null && sortOrder != null) {
            if (sortOrder.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.asc(sortBy)));
            } else if (sortOrder.equalsIgnoreCase("desc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.desc(sortBy)));
            }
        }

        return courseRepo.findAll(spec, pageable);
    }

}
