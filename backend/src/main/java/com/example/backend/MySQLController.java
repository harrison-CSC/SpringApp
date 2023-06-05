package com.example.backend;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MySQLController
{
    @Autowired
    private StudentsRepository studentsRepository;

    @CrossOrigin
    @PostMapping(path="/tryCreateStudent")
    public @ResponseBody String addNewStudent(@RequestBody com.fasterxml.jackson.databind.JsonNode payload)
    {
        String firstName = payload.get("firstName").asText();
        String lastName = payload.get("lastName").asText();
        String gradeYear = payload.get("gradeYear").asText();
        String major = payload.get("major").asText();

        System.out.println("Creating new student with details:");
        System.out.println("    firstName: " + firstName);
        System.out.println("    lastName: " + lastName);
        System.out.println("    gradeYear: " + gradeYear);
        System.out.println("    major: " + major);
        
        studentsRepository.save(new Student(firstName, lastName, gradeYear, major));
        return "Success";
    }

    @CrossOrigin
    @PostMapping(path="/tryUpdateStudent")
    public @ResponseBody String tryUpdateStudent(@RequestBody com.fasterxml.jackson.databind.JsonNode payload)
    {
        int id = payload.get("id").asInt();
        String firstName = payload.get("firstName").asText();
        String lastName = payload.get("lastName").asText();
        String gradeYear = payload.get("gradeYear").asText();
        String major = payload.get("major").asText();

        System.out.println("Updating new student with details:");
        System.out.println("    firstName: " + firstName);
        System.out.println("    lastName: " + lastName);
        System.out.println("    gradeYear: " + gradeYear);
        System.out.println("    major: " + major);
        
        Optional<Student> foundStudent = studentsRepository.findById(id);
        if (foundStudent.isPresent())
        {
            Student toUpdate = foundStudent.get();
            toUpdate.firstName = firstName;
            toUpdate.lastName = lastName;
            toUpdate.gradeYear = gradeYear;
            toUpdate.major = major;
            studentsRepository.save(toUpdate);
            return "success";
        }
        else
            return "error";

    }

    @CrossOrigin
    @GetMapping(path="/getAllStudents")
    public @ResponseBody Iterable<Student> getAllStudents()
    {
        return studentsRepository.findAll();
    }

    @CrossOrigin
    @PostMapping(path="/getStudentById")
    public @ResponseBody Optional<Student> getStudentById(@RequestBody com.fasterxml.jackson.databind.JsonNode payload)
    {
        return studentsRepository.findById(payload.get("id").asInt());
    }

    @CrossOrigin
    @PostMapping(path="/tryDeleteStudent")
    public @ResponseBody String tryDeleteStudent(@RequestBody com.fasterxml.jackson.databind.JsonNode payload)
    {
        studentsRepository.deleteById(payload.get("id").asInt());
        return "Success";
    }
}