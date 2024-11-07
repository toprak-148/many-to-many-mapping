package com.td005.advancedcruddemo.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    //define our fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;


    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="instructor_id")
    private Instructor instructor;
    //define constructor


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review>  reviews;


    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,
                                                CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;



    public Course()
    {

    }
    public Course(String title) {
        this.title = title;
    }


    public List<Student> getStudents() {
        return students;
    }



    public void setStudents(List<Student> students) {
        this.students = students;
    }

    //define getter setter
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }


    //define toString

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }


    //add a conveince method
    public void add(Review review)
    {
        if (reviews == null)
            reviews = new ArrayList<>();

        reviews.add(review);
    }

    //add a conveince method
    public void addStudent(Student theStudent)
    {
        if(students == null)
            students = new ArrayList<>();
        students.add(theStudent);
    }
}
