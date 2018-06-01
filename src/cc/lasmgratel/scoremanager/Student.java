package cc.lasmgratel.scoremanager;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Grade> gradeList = new ArrayList<>();

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSumGrade() {
        double sum = 0;
        for (Grade grade : getGradeList())
            sum += grade.getSumGrade();
        return sum / getGradeList().size();
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gradeList=" + gradeList +
                '}';
    }
}
