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
public class Course {
    @NotEmpty
    private String ID;
    @NotEmpty
    private String name;
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z\s]+", message = "Name should only contain letters")
    private String teacherName;
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z\s]+", message = "School name should only contain letters")
    private String schoolName;
    @NotNull
    @Positive
    private int credit;
    private List<Student> students;

}
