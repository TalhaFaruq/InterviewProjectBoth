package com.interview.coursestudent.Repository;

import com.interview.coursestudent.Entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepo extends JpaRepository<Course, Long> {

    List<Course> findAll(Specification<Course> spec, Pageable pageable);
}
