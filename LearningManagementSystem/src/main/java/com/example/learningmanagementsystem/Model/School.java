package com.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class School {
    @NotEmpty
    private String ID;
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z\s]+", message = "Name should only contain letters")
    private String name;
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]+", message = "City should only contain letters")
    private  String city;
    private List<Teacher> teachers;
    private List<Student> students;
    private List<Course> courses;

}
