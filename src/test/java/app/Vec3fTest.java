package app;

import org.testng.annotations.*;
import static org.testng.Assert.*;

/**
 * App Unit Testing
 * 
 * @author Thiago Rezende
 */
public class Vec3fTest {
    @Test
    public void vec3fDefaultInitialization() {
        Vec3f vec = new Vec3f();
        assertEquals(vec.x, 0.0f);
        assertEquals(vec.y, 0.0f);
        assertEquals(vec.z, 0.0f);
    }

    @Test
    public void vec3fInitialization() {
        Vec3f vec = new Vec3f(1.0f, 2.0f, 3.0f);
        assertEquals(vec.x, 1.0f);
        assertEquals(vec.y, 2.0f);
        assertEquals(vec.z, 3.0f);
    }

    @Test
    public void vec3fAddValue() {
        Vec3f vec = new Vec3f();
        vec.add(2.0f);
        assertEquals(vec.x, 2.0f);
        assertEquals(vec.y, 2.0f);
        assertEquals(vec.z, 2.0f);
    }

    @Test
    public void vec3fSubValue() {
        Vec3f vec = new Vec3f();
        vec.sub(2.0f);
        assertEquals(vec.x, -2.0f);
        assertEquals(vec.y, -2.0f);
        assertEquals(vec.z, -2.0f);
    }

    @Test
    public void vec3fAddOtheVector() {
        Vec3f vec = new Vec3f();
        Vec3f other = new Vec3f(1.0f, 2.0f, 3.0f);
        vec.add(other);
        assertEquals(vec.x, 1.0f);
        assertEquals(vec.y, 2.0f);
        assertEquals(vec.z, 3.0f);
    }

    @Test
    public void vec3fSubOtheVector() {
        Vec3f vec = new Vec3f();
        Vec3f other = new Vec3f(1.0f, 2.0f, 3.0f);
        vec.sub(other);
        assertEquals(vec.x, -1.0f);
        assertEquals(vec.y, -2.0f);
        assertEquals(vec.z, -3.0f);
    }
}
