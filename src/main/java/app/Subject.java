package app;

public class Subject {

    private String m_name;
    private int m_period;
    private int m_valueInCredits;

    public Subject() {
        m_name = "UNKNOWN";
        m_period = 0;
        m_valueInCredits = 0;
    }

    public Subject(String name, int period, int valueInCredits) {
        m_name = name;
        m_period = period;
        m_valueInCredits = valueInCredits;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        m_name = name;
    }

    public int getPeriod() {
        return m_period;
    }

    public void setPeriod(int period) {
        m_period = period;
    }

    public int getValueInCredits() {
        return m_valueInCredits;
    }

    public void setValueInCredits(int valueInCredits) {
        m_valueInCredits = valueInCredits;
    }

    @Override
    public boolean equals(Object obj) {
        Subject other = (Subject) obj;
        return this.getName().equals(other.getName());
    }

    @Override
    public String toString() {
        return "Subject {Name: " + m_name + ", Period: " + m_period + ", Value in credits: " + m_valueInCredits + "}";
    }
}