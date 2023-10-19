import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { StudentComponent } from './Components/student/student.component';
import { CourseComponent } from './Components/course/course.component';
import { StudentCourseComponent } from './Components/student-course/student-course.component';

const routes: Routes = [
  { path: 'students', component: StudentComponent },
  { path: 'courses', component: CourseComponent },
  { path: 'home', component: StudentCourseComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }, // Redirect to /students by default
];

@NgModule({
  declarations: [],
  imports: [CommonModule, RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
