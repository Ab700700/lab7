package com.example.learningmanagementsystem.Controller;


import com.example.learningmanagementsystem.Model.School;
import com.example.learningmanagementsystem.Service.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lms/school")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;


    //c
        @PostMapping("/add")
        public ResponseEntity add(@RequestBody @Valid School school,  Errors errors){
            if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
            schoolService.add(school);
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }
    //r
    @GetMapping("/get")
    public ResponseEntity getAll(){
        return  ResponseEntity.status(HttpStatus.OK).body(schoolService.getSchools());
    }
    //d
    @DeleteMapping("/delete/{id}")
        public ResponseEntity delete(@PathVariable String id){
            if(schoolService.deleteSchool(id)) return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND.toString());
        }

    //u
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id , @RequestBody @Valid School school, Errors errors){
            if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
            if(schoolService.updateSchool(id,school)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
            }

    }

    @PutMapping("/addstudents/{id}")
    public ResponseEntity addStudetns(@PathVariable String id){
            if(schoolService.addStudents(id)) return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("School not found"+HttpStatus.NOT_FOUND.toString());
    }

    @PutMapping("/addteachers/{id}")
    public ResponseEntity addTeachers(@PathVariable String id){
           if (schoolService.addTeacher(id))return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("School not found"+HttpStatus.NOT_FOUND.toString());
    }
    @PutMapping("/addcourses/{id}")
    public ResponseEntity addCourses(@PathVariable String id){
        if(schoolService.addCourses(id)) return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("School not found"+HttpStatus.NOT_FOUND.toString());
    }
}
