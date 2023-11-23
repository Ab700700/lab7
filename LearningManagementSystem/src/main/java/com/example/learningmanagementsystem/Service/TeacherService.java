package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TeacherService {
    List<Teacher> teachers = new ArrayList<>();
    final private CourseService courseService;
    public void add(Teacher teacher){
        teachers.add(teacher);
    }
    public boolean addCourses(String id){
        Teacher t = search(id);
        if(t==null){
            return false;
        }else{
        List<Course> courses = new ArrayList<>();
        for(Course c : courseService.getCourses()){
            if(c.getTeacherName().equals(t.getName())){
                courses.add(c);
                break;
            }
        }
        for (Teacher tt : teachers){
            if(tt.getID().equals(t.getID())){
                tt.setCourses(courses);
                break;
            }
        }
        return true;
        }
    }

    public boolean courseFound(String cid,String id){
        Teacher t = search(id);
        for(Course c : t.getCourses()){
            if(c.getID().equals(cid))return  true;
        }
        return  false;
    }

    public boolean addOneCourse(Course course, String id){
        Teacher t = search(id);
        if(courseFound(course.getID(),id)||t==null){
            return false;
        }
        List<Course> courses = t.getCourses();
        courses.add(course);
        t.setCourses(courses);
        return true;
    }
    public boolean removeCourse(String cid,String id){
            Teacher t = search(id);
            List<Course> tcourses = t.getCourses();
            for(int i = 0 ; i<tcourses.size();i++){
                if(courseFound(cid,id)){
                    tcourses.remove(tcourses.get(i));
                    t.setCourses(tcourses);
                    return true;
                }
            }

        return false;
        }

    public List<Teacher> getTeachers(){
        return teachers;
    }
    public Teacher search(String id){
        for(Teacher t : teachers){
            if(t.getID().equals(id)) return  t;
        }
        return null;
    }

    public boolean deleteTeacher(String id){
        Teacher t = search(id);
        if(t!=null){
            teachers.remove(t);
            return  true;
        }
        return false;
    }

    public boolean updateTeacher(String id ,Teacher teacher){
        for(int i = 0; i<teachers.size();i++){
            if(teachers.get(i).getID().equals(id)){
                teachers.set(i,teacher);
                return true;
            }
        }
        return false;

    }
}
