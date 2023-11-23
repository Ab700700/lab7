package com.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Teacher{
    @NotEmpty
    private String ID;
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z\s]+", message = "Name should only contain letters")
    private String name;
    @NotNull
    @Positive
    private  int age;
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z\s]+", message = "School name should only contain letters")
    private String school;
    @NotEmpty
    private String major;
    private List<Course> courses;

}
