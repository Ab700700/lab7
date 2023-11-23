package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Student;
import com.example.learningmanagementsystem.Model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentService {
    List<Student> students = new ArrayList<>();
    private final CourseService courseService;
    //add
    public void add(Student student){
        students.add(student);
    }
    //read
    public List<Student> getStudents(){
        return students;
    }
    //search
    public Student search(String id){
        for(Student s : students){
            if(s.getID().equals(id)) return  s;
        }
        return null;
    }
    //delete
    public boolean deleteStudent(String id){
        Student s = search(id);
        if(s!=null){
            students.remove(s);
            return  true;
        }
        return false;
    }
    //update
    public boolean updateStudent(String id , Student student){
        for(int i = 0; i<students.size();i++){
            if(students.get(i).getID().equals(id)){
                students.set(i,student);
                return true;
            }
        }
        return false;

    }
    public List<Course> courses(String id){
        List<Course> courses = courseService.getCourses();
        List<Course> newCourses = new ArrayList<>();
        for(Course c : courses){
            List<Student> studentList = c.getStudents();
            for(Student s : studentList){
                if(s.getID().equals(id)) newCourses.add(c);
            }
        }
        return newCourses;
    }


    public boolean addCourses(String id){
       Student s = search(id);
       if(s==null){
           return false;
       }else{
       List<Course> courses = courses(id);
       s.setCourses(courses);
       return true;
       }
    }
    public Course searchCourse(String id){
        for(Course c : courseService.getCourses()){
            if(c.getID().equals(id)) return c;
        }
        return null;
    }
    public boolean addOneCourse(String id ,String cid){
        Course c = searchCourse(cid);
        Student s = search(id);
        if(c==null || s==null){
            return false;
        }else{
            List<Course> courses = s.getCourses();
            courses.add(c);
            s.setCourses(courses);
            return true;
        }
    }

    public boolean removeCourse(String id, String cid){
        Course c = searchCourse(cid);
        Student s = search(id);
        if(c==null|| s == null){
            return  false;
        }else{
            List<Course> courses = s.getCourses();
            List<Student> students= c.getStudents();
            courses.remove(c);
            s.setCourses(courses);
            students.remove(s);
            c.setStudents(students);
            return true;
        }
    }
}
