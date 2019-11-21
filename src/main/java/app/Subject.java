package app;

/**
 * Subject
 * 
 * @author Thiago Rezende
 */
public class Subject {

    private String m_name;
    private int m_period;
    private int m_valueInCredits;

    /**
     * Default constructor
     */
    public Subject() {
        m_name = "UNKNOWN";
        m_period = 0;
        m_valueInCredits = 0;
    }

    /**
     * Complete constructor
     * 
     * @param name           Subject name
     * @param period         Period
     * @param valueInCredits Value in credits
     */
    public Subject(String name, int period, int valueInCredits) {
        m_name = name;
        m_period = period;
        m_valueInCredits = valueInCredits;
    }

    /**
     * Subject name getter
     * 
     * @return String Subject name
     */
    public String getName() {
        return m_name;
    }

    /**
     * Subject name setter
     * 
     * @param name Name to set
     */
    public void setName(String name) {
        m_name = name;
    }

    /**
     * Subject period getter
     * 
     * @return int Subject period
     */
    public int getPeriod() {
        return m_period;
    }

    /**
     * Subject period setter
     * 
     * @param period Period to set
     */
    public void setPeriod(int period) {
        m_period = period;
    }

    /**
     * Subject value in credits getter
     * 
     * @return int Subject value in credits
     */
    public int getValueInCredits() {
        return m_valueInCredits;
    }

    /**
     * Subject value in credits setter
     * 
     * @param valueInCredits Value to set
     */
    public void setValueInCredits(int valueInCredits) {
        m_valueInCredits = valueInCredits;
    }

    /**
     * Equals
     * 
     * @param obj Object to compare
     * @return boolean Result
     */
    @Override
    public boolean equals(Object obj) {
        Subject other = (Subject) obj;
        return this.getName().equals(other.getName());
    }

    /**
     * To string
     * 
     * @return String String with Object's information
     */
    @Override
    public String toString() {
        return "Subject {Name: " + m_name + ", Period: " + m_period + ", Value in credits: " + m_valueInCredits + "}";
    }
}