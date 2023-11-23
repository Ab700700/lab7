package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CourseService {
    List<Course> courses = new ArrayList<>();

    public void add(Course course){
        courses.add(course);
    }

    public List<Course> getCourses(){
        return courses;
    }
    public Course search(String id){
        for(Course c : courses){
            if(c.getID().equals(id)) return  c;
        }
        return null;
    }

    public boolean deleteCourse(String id){
        Course c = search(id);
        if(c!=null){
            courses.remove(c);
            return  true;
        }
        return false;
    }

    public boolean updateCourse(String id , Course course){
        for(int i = 0; i<courses.size();i++){
            if(courses.get(i).getID().equals(id)){
                courses.set(i,course);
                return true;
            }
        }
        return false;

    }

    public List<Student> students(String id){
        for(Course c: courses){
            if(c.getID().equals(id)) return  c.getStudents();
        }
        return new ArrayList<>();
    }
}
