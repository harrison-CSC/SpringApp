package com.example.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int id;
    
    public String firstName;
    public String lastName;
    public String gradeYear;
    public String major;

    public Student(){}

    public Student(String firstName, String lastName, String gradeYear, String major)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gradeYear = gradeYear;
        this.major = major;
    }
}
