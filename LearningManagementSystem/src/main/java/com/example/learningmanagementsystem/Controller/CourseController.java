package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Student;
import com.example.learningmanagementsystem.Service.CourseService;
import com.example.learningmanagementsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lms/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    //c
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        courseService.add(course);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
    }
    //r
    @GetMapping("/get")
    public ResponseEntity getAll(){
        return  ResponseEntity.status(HttpStatus.OK).body(courseService.getCourses());
    }
    //d
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        if(courseService.deleteCourse(id)) return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND.toString());
    }

    //u
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id , @RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        if(courseService.updateCourse(id,course)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }

    }

    @GetMapping("/students/{id}")
    public ResponseEntity getStudetns(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.students(id));
    }
}
