package app;

import java.util.List;

import exceptions.NotFoundException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * School
 * 
 * @author Thiago Rezende
 */
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    private int m_registartionCounter;
    private String m_name;
    private List<Course> m_courses;
    private List<Student> m_students;

    /**
     * Default constructor
     */
    public School(String name) {
        m_registartionCounter = 0;
        m_name = name;
        m_courses = new ArrayList<Course>();
        m_students = new ArrayList<Student>();
    }

    /**
     * Add a student
     * 
     * @param student student to be added
     */
    public void addStudent(Student student) {
        m_registartionCounter++;
        int registration = Integer.parseInt(student.getDateOfEntry().getYear() + "" + m_registartionCounter);
        student.setRegistration(registration);
        m_students.add(student);
    }

    /**
     * Remove a student
     * 
     * @param registration Registration of the student
     * @return boolean Status
     */
    public boolean removeStudent(int registration) {
        for (int i = 0; i < m_students.size(); i++)
            if (m_students.get(i).getRegistration() == registration) {
                m_students.remove(i);
                return true;
            }

        return false;
    }

    /**
     * Add a course
     * 
     * @param course Course to be added
     */
    public void addCourse(Course course) {
        m_courses.add(course);
    }

    /**
     * Remove a course
     * 
     * @param courseTitle Course title
     * @param courseLevel Course level
     * @return boolean Status
     */
    public boolean removeCourse(String courseTitle, int courseLevel) {
        for (int i = 0; i < m_courses.size(); i++)
            if (m_courses.get(i).getTitle().equals(courseTitle) && m_courses.get(i).getLevel() == courseLevel) {
                m_courses.remove(i);
                return true;
            }

        return false;
    }

    /**
     * School name getter
     * 
     * @return String School name
     */
    public String getName() {
        return m_name;
    }

    /**
     * School name setter
     * 
     * @param name School name
     */
    public void setName(String name) {
        m_name = name;
    }

    /**
     * School courses getter
     * 
     * @return List School courses
     */
    public List<Course> getCourses() {
        return m_courses;
    }

    /**
     * School courses setter
     * 
     * @param courses Courses to set
     */
    public void setCourses(List<Course> courses) {
        m_courses = courses;
    }

    /**
     * School Student getter
     * 
     * @return Student School student
     */
    public Student getStudent(int registration) throws NotFoundException {
        for (Student student : m_students)
            if (student.getRegistration() == registration)
                return student;

        throw new NotFoundException("Student Not Found!");
    }

    /**
     * School Students getter
     * 
     * @return List School students
     */
    public List<Student> getStudents() {
        return m_students;
    }

    /**
     * School students setter
     * 
     * @param students Students to set
     */
    public void setStudents(List<Student> students) {
        m_students = students;
    }

    @Override
    public String toString() {
        String out = "-- " + this.getName() + " -- \n - Courses:\n";
        for (Course course : m_courses) {
            out += "  name: " + course.getTitle() + ", lvl: " + course.getLevel() + "\n";
        }

        out += " - Students:\n";

        for (Student student : m_students) {
            out += "  reg: " + student.getRegistration() + ", name: " + student.getName() + ", course: "
                    + student.getCourse().getTitle() + "\n";
        }

        return out;
    }
}