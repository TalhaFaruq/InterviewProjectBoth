package com.interview.coursestudent.Repository;

import com.interview.coursestudent.Entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepo extends JpaRepository<Student, Long> {
    List<Student> findAll(Specification<Student> spec, Pageable pageable);
}
