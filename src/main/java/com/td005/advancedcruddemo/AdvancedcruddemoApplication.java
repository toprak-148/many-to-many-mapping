package com.td005.advancedcruddemo;

import com.td005.advancedcruddemo.dao.AppDao;
import com.td005.advancedcruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AdvancedcruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedcruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao)
	{
		return runner -> {
 			//createCourseAndStudents(appDao);
			 //findCourseAndStudents(appDao);
			//findStudentAndCourses(appDao);
			//addMore(appDao);
//			deleteCourse(appDao);
			deleteStudentById(appDao);
		};
	}


	//many-to-many mapping methods

	private void addMore(AppDao appDao)
	{
		int theId = 2;
		Student tempStudent = appDao.findStudentAndCoursesById(theId);

		Course course = new Course("Rubik's Cube - How to Speed Cube");
		Course course1 = new Course("Atari 2600 - Game Development");

		//add courses to student
		tempStudent.addCourse(course);
		tempStudent.addCourse(course1);

		System.out.println("Saving Student" + tempStudent);
		System.out.println("associated courses : " + tempStudent.getCourses());
		appDao.update(tempStudent);

		System.out.println("Done!");



	}

	private void findStudentAndCourses(AppDao appDao)
	{
		int theId = 2;
		Student student = appDao.findStudentAndCoursesById(theId);
		System.out.println("Loaded student:"+student);
		System.out.println("Course: " + student.getCourses());
		System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDao appDao)
	{
		int theId = 10;

		Course tempCourse = appDao.findCourseAndStudentsByCourseId(theId);

		System.out.println("Loaded course : "+tempCourse);

		System.out.println("Students : " + tempCourse.getStudents());

		System.out.println("Done!");
	}
	private void createCourseAndStudents(AppDao appDao)
	{
		// create a course
		Course tempCourse = new Course("Pacman - How To Score One Million Points");

		// create the students
		Student tempStudent1 = new Student("Ege","Dogan","ege@gmail.com");
		Student tempStudent2 = new Student("Ahmet","Kelek","kelekahmet@gmail.com");



		// add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		// save the course and associated students
		System.out.println("Saving the course" + tempCourse);
		System.out.println("associated students : " + tempCourse.getStudents());
		appDao.save(tempCourse);

		System.out.println("Done!");

	}

	private void deleteCourseAndReviews(AppDao appDao)
	{
		int theId = 10;
		System.out.println("Delete course id : " + theId);

		appDao.deleteCourse(theId);

		System.out.println("Done!");

	}
	private void  retrieveCourseAndReviews(AppDao appDao)
	{
		int theId = 10;
		//get the course and reviews
		Course tempCourse = appDao.findCourseAndReviewsByCourseId(theId);

		//print course
		System.out.println(tempCourse);

		//print  the reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDao appDao)
	{
		//create a course
		Course tempCourse = new Course("Effective Java");

		//add some reviews

		tempCourse.add(new Review("Greate cours... love it!"));
		tempCourse.add(new Review("Cool course cours... job well done!"));
		tempCourse.add(new Review("What a dumb course , you are an idiot"));

		//save the course ... and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDao.save(tempCourse);

	}


	private void deleteStudentById(AppDao appDao)
	{
		int theId = 1;
		System.out.println("Delete student id : " + theId);
		appDao.deleteStudentById(theId);
		System.out.println("Done!");


	}
	/**------------------------------------------------------------------------------------------------------------------*/

	//one-to-one and one-to-many function

	private void deleteCourse(AppDao appDao)
	{
		int deleteCourseId = 10;
		System.out.println("Delete course id : "+deleteCourseId);

		appDao.deleteCourse(deleteCourseId);
		System.out.println("Done!");
	}

	private void updateCourse(AppDao appDao)
	{
		int theId = 10;

		System.out.println("Finding course id : " + theId);
		Course course = appDao.findCourseById(theId);

		System.out.println("Updating course id : " + theId);
		course.setTitle("Enjoy The simple Things");

		appDao.update(course);
		System.out.println("Done!");

	}

	private void updateInstructor(AppDao appDao)
	{
		int id = 1;
		//find the instructor
		System.out.println("Finding instructor id : " + id);
		Instructor instructor = appDao.findInstructorById(id);
		//update the instructor
		System.out.println("Update instructor id : " + id);
		instructor.setLastName("TESTER");
		appDao.update(instructor);

		System.out.println("Done!");

	}

	private void findInstructorWithCoursesJoinFetch(AppDao appDao)
	{
		int theId = 2;

		//find the instructor
		System.out.println("Finding insturctor id :" + theId);
		Instructor tempInstructor = appDao.findInstructorByIdJoinFetch(theId);
		System.out.println("tempInstructor : " + tempInstructor);
		System.out.println("the associated courses : " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDao appDao)
	{
		int theId = 1;
		System.out.println("Finding instructor id : " + theId);
		Instructor tempInstructor = appDao.findInstructorById(theId);
		System.out.println("tempInstructor : " + tempInstructor);

		//find cıyrses for instructor
		System.out.println("Finding courses for instructor id : " + theId);
		List<Course> courses = appDao.findCoursesByInstructorId(theId);
		//associate the boject
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses : " +tempInstructor.getCourses());

		System.out.println("Done!");


	}


	private void findInstructorWithCourses(AppDao appDao)
	{
		int theId = 1;
		System.out.println("Finding instructor id : " + theId);
		Instructor tempInstructor = appDao.findInstructorById(theId);
		System.out.println("tempInstructor : " + tempInstructor);
		System.out.println("the associated courses : " + tempInstructor.getCourses());

		System.out.println("Done!");


	}
	private void createInsturctorWithCourses(AppDao appDao)
	{
		//create the insturctor

		Instructor instructor = new Instructor("Doga","Dogan","dogandoga@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("TalkingYoutube","Video Games");

		instructor.setInstructorDetail(instructorDetail);

		//create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The PaintBall Masterclass");


		instructor.add(tempCourse1);
		instructor.add(tempCourse2);

		//save the instructor
		System.out.println("Saving instructor : " + instructor);
		System.out.println("The courses " + instructor.getCourses());
		appDao.save(instructor);

	}
	private void createInstructor(AppDao appDao)
	{
		//create the insturctor

		Instructor instructor = new Instructor("Doga","Dogan","toprakdgn@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("TalkingYoutube","Talking");

		instructor.setInstructorDetail(instructorDetail);

		//create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The PaintBall Masterclass");


		instructor.add(tempCourse1);
		instructor.add(tempCourse2);

		//save the instructor
		System.out.println("Saving instructor : " + instructor);
		System.out.println("The courses " + instructor.getCourses());
		appDao.save(instructor);
	}

	private void findInstructor(AppDao appDao)
	{
		int theId = 2;
		System.out.println("finding instructor id : " + theId);
		Instructor tempInstructor = appDao.findInstructorById(theId);
		System.out.println("Instructor : " + tempInstructor);
		System.out.println("the associate instructorDetail only : " + tempInstructor.getInstructorDetail());

	}

	public void deleteInstructor(AppDao appDao)
	{
		int id = 1;
		System.out.println("Deleting instructor id : " + id);
		appDao.removeInstructor(id);
		System.out.println("Done!");


	}
	private void findInstructorDetail(AppDao appDao)
	{
		//get the insturcot detail object
		int id = 3;
		InstructorDetail instructorDetail = appDao.findInstructorDetailById(id);

		System.out.println("TempInstructorDetail : " + instructorDetail);

		System.out.println("The assıcşated instructor : " + instructorDetail.getInstructor());

		System.out.println("Done!");

	}

	private void deleteInstructorDetail(AppDao appDao)
	{
		int id = 2;
		System.out.println("Deleting instructor detail id: "+id);
		appDao.deleteInstructorDetailById(id);
		System.out.println("Done");
	}

}
