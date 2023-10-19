import {Component} from '@angular/core';
import {ApiService} from '../../Service/api.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css'],
})
export class CourseComponent {
  courseName: string = '';
  constructor(private apiService: ApiService) {
  }

  saveCourse() {
    const newCourse: { courseName: string } = {
      courseName: this.courseName,
    };

    this.apiService.createCourse(newCourse).subscribe((response) => {
      console.log('Course saved:', response);
    });
  }
}
