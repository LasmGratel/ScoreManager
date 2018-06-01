package cc.lasmgratel.scoremanager;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个科目的成绩.
 */
public class Grade {
    private Course course;
    private List<Integer> dailyGrades = new ArrayList<>();
    private int midGrade, doGrade, endGrade;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Integer> getDailyGrades() {
        return dailyGrades;
    }

    public int getMidGrade() {
        return midGrade;
    }

    public void setMidGrade(int midGrade) {
        this.midGrade = midGrade;
    }

    public int getDoGrade() {
        return doGrade;
    }

    public void setDoGrade(int doGrade) {
        this.doGrade = doGrade;
    }

    public int getEndGrade() {
        return endGrade;
    }

    public void setEndGrade(int endGrade) {
        this.endGrade = endGrade;
    }

    public double getSumGrade() {
        int sum = getMidGrade() + getDoGrade() + getEndGrade();
        for (Integer dailyGrade : getDailyGrades()) {
            sum += dailyGrade;
        }
        return sum / (3.0 + getDailyGrades().size());
    }

    @Override
    public String toString() {
        return "Grade{" +
                "course=" + course +
                ", dailyGrades=" + dailyGrades +
                ", midGrade=" + midGrade +
                ", doGrade=" + doGrade +
                ", endGrade=" + endGrade +
                '}';
    }
}
