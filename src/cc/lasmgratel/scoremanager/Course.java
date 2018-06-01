package cc.lasmgratel.scoremanager;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Course {
    public static final Map<String, Course> COURSE_MAP = new HashMap<>();

    public static Course getOrCreateCourse(String name) {
        if (COURSE_MAP.containsKey(name))
            return COURSE_MAP.get(name);
        Course course = new Course();
        course.setName(name);
        COURSE_MAP.put(name, course);
        return course;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(getName(), course.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }
}
