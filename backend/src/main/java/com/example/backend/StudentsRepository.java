package com.example.backend;

import org.springframework.data.repository.CrudRepository;

public interface StudentsRepository extends CrudRepository<Student, Integer>
{
    
}
