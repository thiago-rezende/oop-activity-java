package app;

import java.time.LocalDate;

public class EnrolledSubject {

    private Subject m_subject;
    private float m_grade;
    private LocalDate m_dateOfRegistration;

    public EnrolledSubject() {
        m_subject = new Subject();
        m_grade = 0;
        m_dateOfRegistration = LocalDate.now();
    }

    public EnrolledSubject(Subject subject, int grade) {
        m_subject = subject;
        m_grade = grade;
    }

    public EnrolledSubject(Subject subject) {
        m_subject = subject;
        m_grade = 0;
    }

    public boolean getApprovalStatus() {
        return this.getGrade() >= 60;
    }

    public Subject getSubject() {
        return m_subject;
    }

    public void setSubject(Subject subject) {
        m_subject = subject;
    }

    public float getGrade() {
        return m_grade;
    }

    public void setGrade(int grade) {
        m_grade = grade;
    }

    public LocalDate getDateOfRegistration() {
        return m_dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        m_dateOfRegistration = dateOfRegistration;
    }

    @Override
    public boolean equals(Object obj) {
        EnrolledSubject other = (EnrolledSubject) obj;
        return this.getSubject().equals(other.getSubject());
    }

    @Override
    public String toString() {
        return "EnrolledSubject {Subject: " + this.getSubject() + ", Grade: " + this.getGrade()
                + ", Date of registration: " + this.getDateOfRegistration() + "}";
    }

}