package app;

import java.io.Serializable;

/**
 * Simple 3D Vector implementation
 * 
 * @author Thiago Rezende
 */
@SuppressWarnings("serial")
public class Vec3f implements Serializable {

    public float x;
    public float y;
    public float z;

    /**
     * Default constructor
     */
    public Vec3f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    /**
     * @param x x coordinate
     * @param y y coodinate
     * @param z z coodinate
     */
    public Vec3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @param value value to be added
     */
    public void add(float value) {
        this.x += value;
        this.y += value;
        this.z += value;
    }

    /**
     * @param other vector to be added
     */
    public void add(Vec3f other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
    }

    /**
     * @param value value to be subtracted
     */
    public void sub(float value) {
        this.x -= value;
        this.y -= value;
        this.z -= value;
    }

    /**
     * @param other vector to be subtracted
     */
    public void sub(Vec3f other) {
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
    }

    /**
     * @param obj object to be compared
     * @return boolean comparation status
     */
    @Override
    public boolean equals(Object obj) {
        Vec3f other = (Vec3f) obj;
        return (this.x == other.x && this.y == other.y && this.z == other.z);
    }

    /**
     * @return String formated stirng of object attributes
     */
    @Override
    public String toString() {
        return "{ X:" + this.x + ", Y:" + this.y + ", Z:" + this.z + " }";
    }

}