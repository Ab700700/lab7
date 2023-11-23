package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.School;
import com.example.learningmanagementsystem.Model.Student;
import com.example.learningmanagementsystem.Model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    List<School> schools = new ArrayList<>();
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CourseService courseService;

    public void add(School school){
        schools.add(school);
    }

    public List<School> getSchools(){
        return schools;
    }
    public School search(String id){
        for(School s : schools){
            if(s.getID().equals(id)) return  s;
        }
        return null;
    }

    public boolean deleteSchool(String id){
        School s = search(id);
        if(s!=null){
            schools.remove(s);
            return  true;
        }
        return false;
    }

    public boolean updateSchool(String id , School school){
        for(int i = 0; i<schools.size();i++){
            if(schools.get(i).getID().equals(id)){
                schools.set(i,school);
                return true;
            }
        }
        return false;

    }

    public boolean addStudents(String id){
        School s = search(id);
        if(s==null) return false;
        List<Student> students = new ArrayList<>();

        for(Student student: studentService.getStudents()){
            if(student.getSchool().equals(s.getName()))students.add(student);
        }
        s.setStudents(students);
        return true;
    }
    public boolean addCourses(String id){
        School s = search(id);
        if(s==null) return false;
        List<Course> courses = new ArrayList<>();

        for(Course course: courseService.getCourses()){
            if(course.getSchoolName().equals(s.getName()))courses.add(course);
        }
        s.setCourses(courses);
        return true;
    }
    public boolean addTeacher(String id){
        School s = search(id);
        if(s==null) return false;

        List<Teacher> teachers = new ArrayList<>();

        for(Teacher teacher: teacherService.getTeachers()){
            if(teacher.getSchool().equals(s.getName()))teachers.add(teacher);
        }
        s.setTeachers(teachers);
        return true;
    }

}
