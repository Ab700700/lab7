package com.example.learningmanagementsystem.Controller;


import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Student;
import com.example.learningmanagementsystem.Model.Teacher;
import com.example.learningmanagementsystem.Service.StudentService;
import com.example.learningmanagementsystem.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lms/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    //c
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        teacherService.add(teacher);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
    }
    //r
    @GetMapping("/get")
    public ResponseEntity getAll(){
        return  ResponseEntity.status(HttpStatus.OK).body(teacherService.getTeachers());
    }
    //d
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        if(teacherService.deleteTeacher(id)) return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND.toString());
    }

    //u
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id , @RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        if(teacherService.updateTeacher(id,teacher)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }

    }


    @PutMapping("/addcourses/{id}")
    public ResponseEntity addCrouses(@PathVariable String id ){
        teacherService.addCourses(id);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());

    }

    @PutMapping("/addonecourse/{id}")
    public  ResponseEntity addOneCourse(@PathVariable String id, @RequestBody @Valid Course course,Errors errors){
        if(teacherService.addOneCourse(course,id)){
            return ResponseEntity.status(HttpStatus.OK).body("Course added");
        }else if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found or the course");
        }
    }

    // cid means course id
    @DeleteMapping("/removcourse/{id}/{cid}")
    public ResponseEntity removeCourse(@PathVariable String id,@PathVariable String cid){
        if(teacherService.removeCourse(cid,id)){
            return ResponseEntity.status(HttpStatus.OK).body("Course removed");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
    }
}
