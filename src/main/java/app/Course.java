package app;

import java.util.List;
import java.util.ArrayList;

public class Course {

    private String m_title;
    private int m_level;
    private List<Subject> m_subjects;
    private int m_integrationPeriods;

    public Course() {
        m_title = "UNKNOWN";
        m_level = 0;
        m_subjects = new ArrayList<Subject>();
        m_integrationPeriods = 0;
    }

    public Course(String title, int level, int integrationPeriods) {
        m_title = title;
        m_level = level;
        m_subjects = new ArrayList<Subject>();
        m_integrationPeriods = integrationPeriods;
    }

    public Course(String title, int level, List<Subject> subjects, int integrationPeriods) {
        m_title = title;
        m_level = level;
        m_subjects = subjects;
        m_integrationPeriods = integrationPeriods;
    }

    public String getTitle() {
        return m_title;
    }

    public void setTitle(String title) {
        m_title = title;
    }

    public int getLevel() {
        return m_level;
    }

    public void setLevel(int level) {
        m_level = level;
    }

    public List<Subject> getSubjects() {
        return m_subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        m_subjects = subjects;
    }

    @Override
    public boolean equals(Object obj) {
        Course other = (Course) obj;
        return (this.getTitle().equals(other.getTitle()) && this.getLevel() == other.getLevel());
    }

    @Override
    public String toString() {
        return "Course {Title: " + this.getTitle() + ", Level: " + this.getLevel() + ", Subjects: " + m_subjects
                + ", Integration periods: " + m_integrationPeriods + "}";
    }
}