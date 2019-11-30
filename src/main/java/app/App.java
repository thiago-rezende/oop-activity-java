package app;

import java.util.List;
import exceptions.IncompleteCourseException;
import exceptions.NotFoundException;
import java.util.ArrayList;

/**
 * @author Thiago Rezende
 */
public class App {

    /**
     * @param args launch options
     */
    public static void main(String[] args) {
        Subject subject = new Subject("Introduction to Programming", 1, 5);
        List<Subject> courseSubjects = new ArrayList<>();
        courseSubjects.add(subject);
        Course course = new Course("Computer Science", 2, courseSubjects, 4);
        School school = new School("Horus Software University (HSU)");
        school.addCourse(course);
        Student student = new Student("Thiago Rezende", school.getCourses().get(0));
        school.addStudent(student);

        try {
            student = school.getStudent(20191);
            student.addEnrolledSubject(student.getCourse().getSubjects().get(0));
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }

        student.getEnrolledSubjects().get(0).addGrade(100);

        try {
            System.out.println(student.getCertificateOfCompletion());
        } catch (IncompleteCourseException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(student);
        System.out.println(school);
    }
}
