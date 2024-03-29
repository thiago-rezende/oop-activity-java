package app;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * EnrolledSubject
 * 
 * @author Thiago Rezende
 */
public class EnrolledSubject implements Serializable, Comparable<EnrolledSubject> {

    private static final long serialVersionUID = 1L;

    private Subject m_subject;
    private float m_grade;
    private LocalDate m_dateOfRegistration;

    /**
     * Default constructor
     */
    public EnrolledSubject() {
        m_subject = new Subject();
        m_grade = 0;
        m_dateOfRegistration = LocalDate.now();
    }

    /**
     * Constructor
     * 
     * @param subject Subject
     * @param grade   Grade
     */
    public EnrolledSubject(Subject subject, int grade) {
        m_subject = subject;
        m_grade = grade;
        m_dateOfRegistration = LocalDate.now();
    }

    /**
     * Constructor (set grade to 0)
     * 
     * @param subject Subject
     */
    public EnrolledSubject(Subject subject) {
        m_subject = subject;
        m_grade = 0;
        m_dateOfRegistration = LocalDate.now();
    }

    /**
     * Add grade
     * 
     * @param grade Grade
     */
    public void addGrade(float grade) {
        m_grade += grade;
    }

    /**
     * Approval status
     * 
     * @return boolean Status
     */
    public boolean getApprovalStatus() {
        return this.getGrade() >= 60;
    }

    /**
     * Subject getter
     * 
     * @return Subject Subject
     */
    public Subject getSubject() {
        return m_subject;
    }

    /**
     * Subject setter
     * 
     * @param subject Subject to set
     */
    public void setSubject(Subject subject) {
        m_subject = subject;
    }

    /**
     * Grade getter
     * 
     * @return float Grade
     */
    public float getGrade() {
        return m_grade;
    }

    /**
     * Grade setter
     * 
     * @param grade Grade to set
     */
    public void setGrade(int grade) {
        m_grade = grade;
    }

    /**
     * Date of registration getter
     * 
     * @return LocalDate Date of registration
     */
    public LocalDate getDateOfRegistration() {
        return m_dateOfRegistration;
    }

    /**
     * Date of registration setter
     * 
     * @param dateOfRegistration Date to set
     */
    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        m_dateOfRegistration = dateOfRegistration;
    }

    /**
     * Compares an EnrolledSubject to another
     * 
     * @param other EnrolledSubject to be compared
     * @return Comparation result
     */
    @Override
    public int compareTo(EnrolledSubject other) {
        return this.getSubject().compareTo(other.getSubject());
    }

    /**
     * Equals
     * 
     * @param obj Ojbect to compare
     * @return boolean Result
     */
    @Override
    public boolean equals(Object obj) {
        EnrolledSubject other = (EnrolledSubject) obj;
        return this.getSubject().equals(other.getSubject());
    }

    /**
     * To string
     * 
     * @return String String with object's information
     */
    @Override
    public String toString() {
        return "EnrolledSubject {Subject: " + this.getSubject() + ", Grade: " + this.getGrade()
                + ", Date of registration: " + this.getDateOfRegistration() + "}";
    }

}