import {Component} from '@angular/core';
import {ApiService} from '../../Service/api.service';
import {Course} from 'src/app/Models/course.model';
import {Student} from 'src/app/Models/student.model';

@Component({
  selector: 'app-student-course',
  templateUrl: './student-course.component.html',
  styleUrls: ['./student-course.component.css'],
})
export class StudentCourseComponent {
  studentId!: number;
  courseIds!: string;
  studentIdUnassign!: number;
  courseIdsUnassign!: string;
  courses: Course[] = [];
  students: Student[] = [];

  constructor(private apiService: ApiService) {
  }

  assignCoursesToStudent() {
    const studentId = this.studentId;
    const courseIds = this.courseIds.split(',').map((id) => +id);

    this.apiService
      .assignCoursesToStudent(studentId, courseIds)
      .subscribe((response) => {
        console.log('Courses assigned to student:', response);
      });
  }

  unassignCoursesFromStudent() {
    const studentId = this.studentIdUnassign;
    const courseIds = this.courseIdsUnassign.split(',').map((id) => +id);

    this.apiService
      .unassignCoursesFromStudent(studentId, courseIds)
      .subscribe((response) => {
        console.log('Courses unassigned from student:', response);
      });
  }

  searchCourses() {
    this.apiService.searchCourses().subscribe((response) => {
      console.log('Courses assigned to student:', response);
      this.courses = response;
    });
  }

  searchStudents() {
    this.apiService.searchStudents().subscribe((response) => {
      console.log(' students:', response);
      this.students = response;
    });
  }
}
