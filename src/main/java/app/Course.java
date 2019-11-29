package app;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Course
 * 
 * @author Thiago Rezende
 */
@SuppressWarnings("serial")
public class Course implements Serializable {

    private String m_title;
    private int m_level;
    private List<Subject> m_subjects;
    private int m_integrationPeriods;

    /**
     * Default constructor
     */
    public Course() {
        m_title = "UNKNOWN";
        m_level = 0;
        m_subjects = new ArrayList<Subject>();
        m_integrationPeriods = 0;
    }

    /**
     * Constructor
     * 
     * @param title              Course title
     * @param level              Course level
     * @param integrationPeriods Course integration periods
     */
    public Course(String title, int level, int integrationPeriods) {
        m_title = title;
        m_level = level;
        m_subjects = new ArrayList<Subject>();
        m_integrationPeriods = integrationPeriods;
    }

    /**
     * Constructor
     * 
     * @param title              Course title
     * @param level              Course level
     * @param subjects           Coures subjects
     * @param integrationPeriods Course integration periods
     */
    public Course(String title, int level, List<Subject> subjects, int integrationPeriods) {
        m_title = title;
        m_level = level;
        m_subjects = subjects;
        m_integrationPeriods = integrationPeriods;
    }

    /**
     * Course title getter
     * 
     * @return String Course title
     */
    public String getTitle() {
        return m_title;
    }

    /**
     * Course title setter
     * 
     * @param title Title to set
     */
    public void setTitle(String title) {
        m_title = title;
    }

    /**
     * Course level getter
     * 
     * @return int Course level
     */
    public int getLevel() {
        return m_level;
    }

    /**
     * Course level setter
     * 
     * @param level Level to set
     */
    public void setLevel(int level) {
        m_level = level;
    }

    /**
     * Course subjects getter
     * 
     * @return List Course subjects
     */
    public List<Subject> getSubjects() {
        return m_subjects;
    }

    /**
     * Course subjects setter
     * 
     * @param subjects Subjects to set
     */
    public void setSubjects(List<Subject> subjects) {
        m_subjects = subjects;
    }

    /**
     * Equals
     * 
     * @param obj Object to compare
     * @return boolean Result
     */
    @Override
    public boolean equals(Object obj) {
        Course other = (Course) obj;
        return (this.getTitle().equals(other.getTitle()) && this.getLevel() == other.getLevel());
    }

    /**
     * To string
     * 
     * @return String String with Object's information
     */
    @Override
    public String toString() {
        return "Course {Title: " + this.getTitle() + ", Level: " + this.getLevel() + ", Subjects: " + m_subjects
                + ", Integration periods: " + m_integrationPeriods + "}";
    }
}