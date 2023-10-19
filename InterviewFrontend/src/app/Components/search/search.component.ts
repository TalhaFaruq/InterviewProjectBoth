import {Component} from '@angular/core';
import {ApiService} from 'src/app/Service/api.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent {
  names: string[] = [];
  searchName: string = '';

  constructor(private apiService: ApiService) {
  }

  searchStudentsOrCourses(searchString: any) {
    this.names = [];
    this.searchStudent(searchString.target.value);
    if (this.names.length <= 0) {
      this.searchCourses(searchString.target.value);
    }
  }

  searchStudent(name: string) {
    this.searchName = 'Students';
    this.apiService.searchStudents(name).subscribe((response) => {
      response.forEach((s) => this.names.push(s.studentName));
    });
  }

  searchCourses(name: string) {
    this.searchName = 'Courses';
    this.apiService.searchCourses(name).subscribe((response) => {
      response.forEach((c) => this.names.push(c.courseName));
    });
  }
}
