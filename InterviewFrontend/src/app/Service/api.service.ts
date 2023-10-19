import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../Models/course.model';
import { Student } from '../Models/student.model';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  createCourse(course: { courseName: string }): Observable<Course> {
    return this.http.post<Course>(
      `${this.baseUrl}/courses/createCourse`,
      course
    );
  }

  getCourseById(id: number): Observable<Course> {
    return this.http.get<Course>(`${this.baseUrl}/courses/getByIdCourse/${id}`);
  }

  deleteCourse(id: number): Observable<Course> {
    return this.http.delete<Course>(
      `${this.baseUrl}/courses/deleteCourse/${id}`
    );
  }

  searchCourses(
    courseName?: string,
    page?: number,
    pageSize?: number,
    id?: number,
    sortBy?: string,
    sortOrder?: string
  ): Observable<Course[]> {
    let params: any = {
      page: page?.toString(),
      pageSize: pageSize?.toString(),
    };

    if (courseName) {
      params.courseName = courseName;
    }

    if (id) {
      params.id = id.toString();
    }

    if (sortBy) {
      params.sortBy = sortBy;
    }

    if (sortOrder) {
      params.sortOrder = sortOrder;
    }

    return this.http.get<Course[]>(`${this.baseUrl}`, { params });
  }

  createStudent(student: {
    phoneNumber: string;
    studentName: string;
  }): Observable<Student> {
    return this.http.post<Student>(
      `${this.baseUrl}/students/createStudent`,
      student
    );
  }

  getStudentById(id: number): Observable<Student> {
    return this.http.get<Student>(`${this.baseUrl}/getByIdStudent/${id}`);
  }

  deleteStudent(id: number): Observable<Student> {
    return this.http.delete<Student>(
      `${this.baseUrl}/students/deleteStudent/${id}`
    );
  }

  searchStudents(
    studentName?: string,
    page?: number,
    pageSize?: number,
    phoneNumber?: string,
    id?: number,
    sortBy?: string,
    sortOrder?: string
  ): Observable<Student[]> {
    let params: any = {
      page: page?.toString(),
      pageSize: pageSize?.toString(),
    };

    if (studentName) {
      params.studentName = studentName;
    }

    if (phoneNumber) {
      params.phoneNumber = phoneNumber;
    }

    if (id) {
      params.id = id.toString();
    }

    if (sortBy) {
      params.sortBy = sortBy;
    }

    if (sortOrder) {
      params.sortOrder = sortOrder;
    }

    return this.http.get<Student[]>(`${this.baseUrl}`, { params });
  }

  assignCoursesToStudent(
    studentId: number,
    courseIdList: number[]
  ): Observable<Student> {
    return this.http.post<Student>(
      `${this.baseUrl}/students/${studentId}/assign-courses`,
      courseIdList
    );
  }

  unassignCoursesFromStudent(
    studentId: number,
    courseIdList: number[]
  ): Observable<Student> {
    return this.http.post<Student>(
      `${this.baseUrl}/students/${studentId}/unassign-courses`,
      courseIdList
    );
  }
}
