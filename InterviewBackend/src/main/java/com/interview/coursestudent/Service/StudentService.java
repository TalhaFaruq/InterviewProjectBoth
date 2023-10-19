package com.interview.coursestudent.Service;


import com.interview.coursestudent.DTO.StudentDTO;
import com.interview.coursestudent.Entity.Course;
import com.interview.coursestudent.Entity.Student;
import com.interview.coursestudent.Repository.ICourseRepo;
import com.interview.coursestudent.Repository.IStudentRepo;
import com.interview.coursestudent.Service.InterfaceService.IStudentService;
import com.interview.coursestudent.mapper.IStudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final IStudentRepo studentRepo;
    private final ICourseRepo courseRepo;

    @Override
    public StudentDTO mergeStudent(StudentDTO incomingStudent) {
        return IStudentMapper.INSTANCE.studentToStudentDTO(studentRepo.save(IStudentMapper.INSTANCE.studentDTOToStudent(incomingStudent)));
    }

    @Override
    public StudentDTO getById(Long id) {
        return IStudentMapper.INSTANCE.studentToStudentDTO(studentRepo.getById(id));
    }

    @Override
    public StudentDTO deleteStudent(Long id) {
        StudentDTO studentDTO = IStudentMapper.INSTANCE.studentToStudentDTO(studentRepo.findById(id).get());
        studentDTO.setDeleted(Boolean.TRUE);
        return IStudentMapper.INSTANCE.studentToStudentDTO(studentRepo.save(IStudentMapper.INSTANCE.studentDTOToStudent(studentDTO)));
    }

    @Override
    public List<Student> searchStudent(Pageable pageable, String studentName, String phoneNumber, Long id, String sortBy, String sortOrder) {
        Specification<Student> spec = Specification.where(null);
        if (studentName != null && !studentName.isBlank()) {
            spec = spec.and((root, query, builder) -> builder.like(root.get("studentName"), "%" + studentName + "%"));
        }
        if (phoneNumber != null && !phoneNumber.isBlank()) {
            spec = spec.and((root, query, builder) -> builder.like(root.get("phoneNumber"), "%" + phoneNumber + "%"));
        }
        if (id != null) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("id"), id));
        }

        if (sortBy != null && sortOrder != null) {
            if (sortOrder.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.asc(sortBy)));
            } else if (sortOrder.equalsIgnoreCase("desc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.desc(sortBy)));
            }
        }

        return studentRepo.findAll(spec, pageable);
    }

    @Override
    public StudentDTO assignCourseToStudent(Long studentId, List<Long> courseIdList) {
        Student student = studentRepo.getById(studentId);
        Set<Course> coursesToAssign = courseIdList.stream().map(courseRepo::getById) // Filter out any null StudentDTOs
                .collect(Collectors.toSet());

        if (student.getCourses() == null) student.setCourses(coursesToAssign);
        else student.getCourses().addAll(coursesToAssign);

        studentRepo.save(student);

        return IStudentMapper.INSTANCE.studentToStudentDTO(student);
    }

    @Override
    public StudentDTO unassignCourseFromStudent(Long studentId, List<Long> courseIdList) {
        Student student = studentRepo.getById(studentId);
        Set<Course> coursesToUnassign = courseIdList.stream().map(courseRepo::getById).collect(Collectors.toSet());

        if (student.getCourses() != null) {
            student.getCourses().removeAll(coursesToUnassign);
            studentRepo.save(student);
        }

        return IStudentMapper.INSTANCE.studentToStudentDTO(student);
    }
}
