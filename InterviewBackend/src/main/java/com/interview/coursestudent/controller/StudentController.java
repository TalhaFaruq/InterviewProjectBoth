package com.interview.coursestudent.controller;

import com.interview.coursestudent.DTO.StudentDTO;
import com.interview.coursestudent.Entity.Student;
import com.interview.coursestudent.Service.InterfaceService.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final IStudentService studentService;

    @Autowired
    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/createStudent")
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.mergeStudent(studentDTO);
    }

    @GetMapping("/getByIdStudent/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public StudentDTO deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping
    public ResponseEntity<List<Student>> searchStudents(Pageable pageable, @RequestParam(required = false) String studentName, @RequestParam(required = false) String phoneNumber, @RequestParam(required = false) Long id, @RequestParam(required = false) String sortBy, @RequestParam(required = false) String sortOrder) {
        List<Student> students = studentService.searchStudent(pageable, studentName, phoneNumber, id, sortBy, sortOrder);
        return ResponseEntity.ok(students);
    }

    @PostMapping("/{studentId}/assign-courses")
    public ResponseEntity<StudentDTO> assignCoursesToStudent(@PathVariable Long studentId, @RequestBody List<Long> courseIdList) {

        StudentDTO studentDTO = studentService.assignCourseToStudent(studentId, courseIdList);

        if (studentDTO != null) {
            return ResponseEntity.ok(studentDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{studentId}/unassign-courses")
    public ResponseEntity<StudentDTO> unassignCoursesFromStudent(@PathVariable Long studentId, @RequestBody List<Long> courseIdList) {

        StudentDTO updatedStudent = studentService.unassignCourseFromStudent(studentId, courseIdList);

        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
