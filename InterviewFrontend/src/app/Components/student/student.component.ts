import { Component } from '@angular/core';
import { ApiService } from '../../Service/api.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css'],
})
export class StudentComponent {
  studentName: string = '';
  phoneNumber: string = '';

  constructor(private studentService: ApiService) {}

  saveStudent() {
    const newStudent = {
      studentName: this.studentName,
      phoneNumber: this.phoneNumber,
    };

    console.log(newStudent);

    this.studentService.createStudent(newStudent).subscribe((response: any) => {
      console.log('Student saved:', response);
    });
  }
}
