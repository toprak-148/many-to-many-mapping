package com.td005.advancedcruddemo.dao;

import com.td005.advancedcruddemo.entity.Course;
import com.td005.advancedcruddemo.entity.Instructor;
import com.td005.advancedcruddemo.entity.InstructorDetail;
import com.td005.advancedcruddemo.entity.Student;

import java.util.List;

public interface AppDao {

    void save(Instructor instructor);
    Instructor findInstructorById(int theId);

    void removeInstructor(int deleteId);
    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course>  findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor instructor);

    void update(Course tempCourse);

    Course findCourseById(int theId);
    void deleteCourse(int theId);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentsByCourseId(int theId);

    Student findStudentAndCoursesById(int theId);

    void update(Student tempStudent);

    void deleteStudentById(int theId);




}
