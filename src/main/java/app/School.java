package app;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * School
 * 
 * @author Thiago Rezende
 */
@SuppressWarnings("serial")
public class School implements Serializable {

    private List<Course> m_courses;
    private List<Student> m_students;

    /**
     * Default constructor
     */
    public School() {
        m_courses = new ArrayList<Course>();
        m_students = new ArrayList<Student>();
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
}