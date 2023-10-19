import {Course} from "./course.model";

export class Student {
  id: number;
  studentName: string;
  phoneNumber: string;
  deleted: boolean;
  courses: Course[]; // Assuming you have a Course model

  constructor() {
    this.id = 0;
    this.studentName = '';
    this.phoneNumber = '';
    this.deleted = false;
    this.courses = [];
  }
}
