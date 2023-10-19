package com.interview.coursestudent.Service.InterfaceService;

import com.interview.coursestudent.DTO.StudentDTO;
import com.interview.coursestudent.Entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public interface IStudentService {

    StudentDTO mergeStudent(@NotNull StudentDTO incomingStudent);

    public StudentDTO getById(Long id);

    StudentDTO deleteStudent(Long id);

    List<Student> searchStudent(Pageable pageable, String name, String phoneNumber, Long id, String sortBy, String sortOrder);

    public StudentDTO assignCourseToStudent(Long courseId, List<Long> studentIdList);

    public StudentDTO unassignCourseFromStudent(Long studentId, List<Long> courseIdList);

}
