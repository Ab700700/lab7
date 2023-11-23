package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Model.Student;
import com.example.learningmanagementsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lms/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    //c
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        studentService.add(student);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
    }
    //r
    @GetMapping("/get")
    public ResponseEntity getAll(){
        return  ResponseEntity.status(HttpStatus.OK).body(studentService.getStudents());
    }
    //d
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        if(studentService.deleteStudent(id)) return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND.toString());
    }

    //u
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id , @RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        if(studentService.updateStudent(id,student)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }

    }

    @PutMapping("/addcourses/{id}")
    public ResponseEntity addCourses(@PathVariable String id){
        if(studentService.addCourses(id)){
            return  ResponseEntity.status(HttpStatus.OK).body("Courses added");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not there |"+HttpStatus.NOT_FOUND.toString());
        }
    }

    @PutMapping("/addcourse/{id}/{cid}")
    public ResponseEntity addCourse(@PathVariable String id , String cid){
        if(studentService.addOneCourse(id,cid)){
            return ResponseEntity.status(HttpStatus.OK).body("Course added");
        }else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course or student not found"+HttpStatus.NOT_FOUND.toString());
        }
    }

    @DeleteMapping("/removecourse/{id}/{cid}")
    public ResponseEntity removeCourse(@PathVariable String id, String cid){
        if(studentService.removeCourse(id,cid)){
            return ResponseEntity.status(HttpStatus.OK).body("Course removed"+HttpStatus.OK.toString());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course or student not found"+HttpStatus.NOT_FOUND.toString());
        }
    }

}
