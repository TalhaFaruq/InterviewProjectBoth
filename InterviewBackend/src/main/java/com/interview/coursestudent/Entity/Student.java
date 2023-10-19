package com.interview.coursestudent.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Primary Key

    @Column(name = "studentName", nullable = false)
    private String studentName;

    @Column(name = "phoneNumber", nullable = true)
    private String phoneNumber;

    @Column(name = "delete", nullable = false)
    private Boolean deleted = Boolean.FALSE; //For soft deleting record from database

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();
}
