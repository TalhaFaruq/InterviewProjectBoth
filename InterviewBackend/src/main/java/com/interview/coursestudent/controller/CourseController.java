package com.interview.coursestudent.controller;

import com.interview.coursestudent.DTO.CourseDTO;
import com.interview.coursestudent.Entity.Course;
import com.interview.coursestudent.Service.InterfaceService.ICourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ICourseService courseService;

    @PostMapping("/createCourse")
    public ResponseEntity<CourseDTO> addNewCourse(@RequestBody CourseDTO course) {
        return ResponseEntity.ok(courseService.mergeCourse(course));
    }

    @GetMapping("/getByIdCourse/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO course = courseService.getById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<CourseDTO> deleteCourse(@PathVariable Long id) {
        CourseDTO courseDTO = courseService.deleteCourse(id);
        if (courseDTO != null) {
            return ResponseEntity.ok(courseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Course>> searchCourses(Pageable pageable, @RequestParam(required = false) String courseName, @RequestParam(required = false) Long id, @RequestParam(required = false) String sortBy, @RequestParam(required = false) String sortOrder) {
        List<Course> courses = courseService.searchCourse(pageable, courseName, id, sortBy, sortOrder);
        return ResponseEntity.ok(courses);
    }

}