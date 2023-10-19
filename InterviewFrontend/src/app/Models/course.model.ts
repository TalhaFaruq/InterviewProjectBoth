import {Student} from "./student.model";

export class Course {
  id: number;
  courseName: string;
  deleted: boolean;
  students: Student[];

  constructor() {
    this.id = 0;
    this.courseName = '';
    this.deleted = false;
    this.students = [];
  }
}
