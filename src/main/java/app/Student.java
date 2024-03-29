package app;

import java.util.List;

import exceptions.IncompleteCourseException;

import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Student
 * 
 * @author Thiago Rezende
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private int m_registration;
    private String m_name;
    private Course m_course;
    private List<EnrolledSubject> m_enrolledSubjects;
    private LocalDate m_dateOfEntry;
    private LocalDate m_dateOfCompletion;

    /**
     * Default constructor
     */
    public Student() {
        m_registration = 0;
        m_name = "UNKNOWN";
        m_course = null;
        m_enrolledSubjects = new ArrayList<EnrolledSubject>();
        m_dateOfEntry = null;
        m_dateOfCompletion = null;
    }

    /**
     * Complete constructor
     * 
     * @param registration Registration number
     * @param name         Student name
     * @param course       Course
     */
    public Student(int registration, String name, Course course) {
        m_registration = registration;
        m_name = name;
        m_course = course;
        m_enrolledSubjects = new ArrayList<EnrolledSubject>();

        // Automatic subject enrollement
        // for (Subject subject : m_course.getSubjects())
        // m_enrolledSubjects.add(new EnrolledSubject(subject));

        m_dateOfEntry = LocalDate.now();
        m_dateOfCompletion = null;
    }

    /**
     * Constructor
     * 
     * @param name   Student name
     * @param course Course
     */
    public Student(String name, Course course) {
        m_registration = 0;
        m_name = name;
        m_course = course;
        m_enrolledSubjects = new ArrayList<EnrolledSubject>();

        // Automatic subject enrollement
        // for (Subject subject : m_course.getSubjects())
        // m_enrolledSubjects.add(new EnrolledSubject(subject));

        m_dateOfEntry = LocalDate.now();
        m_dateOfCompletion = null;
    }

    /**
     * Student approval status getter
     * 
     * @return Approval status
     */
    public boolean getApprovalStatus() {
        if (m_enrolledSubjects.size() < m_course.getSubjects().size())
            return false;

        for (EnrolledSubject subject : m_enrolledSubjects)
            if (!subject.getApprovalStatus())
                return false;

        return true;
    }

    /**
     * Get academic performace coefficient
     * 
     * @return float academic performace coefficient
     */
    public float getAcademicPerformanceCoefficient() {
        // APC = ((Cd1*N1) + ... + (Cdn*Nn)) / (Cd1 + ... + Cdn)
        float numerator = 0.0f;
        float denominator = 0.0f;

        for (EnrolledSubject subject : m_enrolledSubjects) {
            numerator += subject.getSubject().getValueInCredits() * subject.getGrade();
            denominator += subject.getSubject().getValueInCredits();
        }

        return numerator / denominator;
    }

    /**
     * Get certificate of conclusion
     * 
     * @return String Certificate
     * @throws IncompleteCourseException Incomplete course
     */
    public String getCertificateOfCompletion() throws IncompleteCourseException {
        if (m_enrolledSubjects.size() < m_course.getSubjects().size())
            throw new IncompleteCourseException("Did not complete all registrations!");

        for (EnrolledSubject subject : m_enrolledSubjects)
            if (!subject.getApprovalStatus())
                throw new IncompleteCourseException("Insufficient grades!");

        this.setDateOfCompletion(LocalDate.now());

        return "Cetificate of Conclusion\n" + "We certify that " + this.getName() + " has completed the course of "
                + this.getCourse().getTitle() + " on " + this.getDateOfCompletion();
    }

    /**
     * Add an EnrolledSubject
     * 
     * @param subject Subject to be added
     */
    public void addEnrolledSubject(Subject subject) {
        m_enrolledSubjects.add(new EnrolledSubject(subject));
    }

    /**
     * Remove an EnrolledSubject
     * 
     * @param subjectName Name of the subject to be removed
     * @return boolean status
     */
    public boolean removeEnrolledSubject(String subjectName) {
        for (int i = 0; i < m_enrolledSubjects.size(); i++)
            if (m_enrolledSubjects.get(i).getSubject().getName().equals(subjectName)) {
                m_enrolledSubjects.remove(i);
                return true;
            }

        return false;
    }

    /**
     * Registration getter
     * 
     * @return int Registration number
     */
    public int getRegistration() {
        return m_registration;
    }

    /**
     * Registration setter
     * 
     * @param registration Registration number
     */
    public void setRegistration(int registration) {
        m_registration = registration;
    }

    /**
     * Student name getter
     * 
     * @return String Student name
     */
    public String getName() {
        return m_name;
    }

    /**
     * Studnet name setter
     * 
     * @param name Student name
     */
    public void setName(String name) {
        m_name = name;
    }

    /**
     * Course getter
     * 
     * @return Course Course
     */
    public Course getCourse() {
        return m_course;
    }

    /**
     * Course setter
     * 
     * @param course Course to set
     */
    public void setCourse(Course course) {
        m_course = course;
    }

    /**
     * EnrolledSubjects getter
     * 
     * @return List EnrolledSubjects
     */
    public List<EnrolledSubject> getEnrolledSubjects() {
        return m_enrolledSubjects;
    }

    /**
     * EnrolledSubjects setter
     * 
     * @param enrolledSubjects EnrolledSubjects to set
     */
    public void setEnrolledSubjects(List<EnrolledSubject> enrolledSubjects) {
        m_enrolledSubjects = enrolledSubjects;
    }

    /**
     * Date of entry getter
     * 
     * @return LocalDate Date of entry
     */
    public LocalDate getDateOfEntry() {
        return m_dateOfEntry;
    }

    /**
     * Date of entry setter
     * 
     * @param dateOfEntry Date to set
     */
    public void setDateOfEntry(LocalDate dateOfEntry) {
        m_dateOfEntry = dateOfEntry;
    }

    /**
     * Date of completion getter
     * 
     * @return LocalDate Date of completion
     */
    public LocalDate getDateOfCompletion() {
        return m_dateOfCompletion;
    }

    /**
     * Date of completion setter
     * 
     * @param dateOfCompletion Date to set
     */
    public void setDateOfCompletion(LocalDate dateOfCompletion) {
        m_dateOfCompletion = dateOfCompletion;
    }

    /**
     * Equals
     * 
     * @param obj Object to compare
     * @return boolean Result
     */
    @Override
    public boolean equals(Object obj) {
        Student other = (Student) obj;
        return this.getRegistration() == other.getRegistration();
    }

    /**
     * To string
     * 
     * @return String String with object's information
     */
    @Override
    public String toString() {
        return "Student {Registration: " + m_registration + ", Name: " + this.getName() + ", Course: "
                + this.getCourse() + ", Date of entry: " + this.getDateOfEntry() + ", Date of completion: "
                + (this.getDateOfCompletion() == null ? "In Progress" : this.getDateOfCompletion()) + "}";
    }
}