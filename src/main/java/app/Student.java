package app;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Student {
    private int m_registration;
    private String m_name;
    private Course m_course;
    private List<EnrolledSubject> m_enrolledSubjects;
    private LocalDate m_dateOfEntry;
    private LocalDate m_dateOfCompletion;

    public Student() {
        m_registration = 0;
        m_name = "UNKNOWN";
        m_course = null;
        m_enrolledSubjects = new ArrayList<EnrolledSubject>();
        m_dateOfEntry = null;
        m_dateOfCompletion = null;
    }

    public Student(int registration, String name, Course course) {
        m_registration = registration;
        m_name = name;
        m_course = course;
        // TODO: add all the subjects from the course
        m_enrolledSubjects = null;
        m_dateOfEntry = LocalDate.now();
        m_dateOfCompletion = null;
    }

    public int getRegistration() {
        return m_registration;
    }

    public void setRegistration(int registration) {
        m_registration = registration;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        m_name = name;
    }

    public Course getCourse() {
        return m_course;
    }

    public void setCourse(Course course) {
        m_course = course;
    }

    public List<EnrolledSubject> getEnrolledSubjects() {
        return m_enrolledSubjects;
    }

    public void getEnrolledSubjects(List<EnrolledSubject> enrolledSubjects) {
        m_enrolledSubjects = enrolledSubjects;
    }

    public LocalDate getDateOfEntry() {
        return m_dateOfEntry;
    }

    public void setDateOfEntry(LocalDate dateOfEntry) {
        m_dateOfEntry = dateOfEntry;
    }

    public LocalDate getDateOfCompletion() {
        return m_dateOfCompletion;
    }

    public void setDateOfCompletion(LocalDate dateOfCompletion) {
        m_dateOfCompletion = dateOfCompletion;
    }

    @Override
    public boolean equals(Object obj) {
        Student other = (Student) obj;
        return this.getRegistration() == other.getRegistration();
    }

    @Override
    public String toString() {
        return "Student {Registration: " + m_registration + ", Name: " + this.getName() + ", Course: "
                + this.getCourse() + ", Date of entry: " + this.getDateOfEntry() + ", Date of completion: "
                + (this.getDateOfCompletion() != null ? "In Progress" : this.getDateOfCompletion()) + "}";
    }
}