package cc.lasmgratel.scoremanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ScoreManager {
    private static final List<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static Grade inputGrade() {
        Grade grade = new Grade();
        System.out.print("请输入课程名称: ");
        grade.setCourse(Course.getOrCreateCourse(scanner.next()));

        System.out.print("请输入期中成绩: ");
        grade.setDoGrade(scanner.nextInt());

        System.out.print("请输入期末成绩: ");
        grade.setEndGrade(scanner.nextInt());

        System.out.print("请输入实践成绩: ");
        grade.setDoGrade(scanner.nextInt());

        System.out.print("请输入该科目一共有几个平时成绩: ");
        int dailyNum = scanner.nextInt();
        for (int i = 0; i < dailyNum; i++) {
            System.out.printf("请输入第%d个成绩: ", i + 1);
            grade.getDailyGrades().add(scanner.nextInt());
        }

        System.out.printf("添加成功, 该课程为%s\n", grade.toString());
        return grade;
    }

    public static void inputStudents() {
        System.out.print("请输入学生数: ");
        int studentNum = scanner.nextInt();
        for (int i = 0; i < studentNum; i++) {
            Student student = new Student();

            System.out.print("请输入学生名: ");
            student.setName(scanner.next());

            System.out.print("请输入学生所学的课程数: ");
            int gradeNum = scanner.nextInt();
            for (int j = 0; j < gradeNum; j++)
                student.getGradeList().add(inputGrade());

            System.out.println("录入 " + student + " 成功");
            students.add(student);
        }
    }

    public static void queryStudent() {
        System.out.print("请输入学生名: ");
        Optional<Student> studentOptional = students.stream().filter(student1 -> student1.getName().equals(scanner.next())).findAny();
        if (!studentOptional.isPresent()) {
            System.out.println("学生名输入错误, 请重新输入");
            queryStudent();
            return;
        }

        Student student = studentOptional.get();
        System.out.printf("学生 %s 的总评成绩为: %f, 详细成绩为: \n", student.getName(), student.getSumGrade());

        for (Grade grade : student.getGradeList()) {
            printGrade(grade);
        }
    }

    public static void printGrade(Grade grade) {
        System.out.printf("科目 %s: 平时成绩 %s, 期中成绩 %d, 期末成绩 %d, 实践成绩 %d, 总评成绩 %f\n", grade.getCourse().getName(), grade.getDailyGrades(), grade.getMidGrade(), grade.getEndGrade(), grade.getDoGrade(), grade.getSumGrade());
    }

    public static void queryCourse() {
        System.out.print("请输入课程名: ");
        String courseName = scanner.next();

        if (!Course.COURSE_MAP.containsKey(courseName)) {
            System.out.println("课程名不存在, 目前只有 " + Course.COURSE_MAP.keySet() + " 可供选择!");
            queryCourse();
            return;
        }

        Course course = Course.COURSE_MAP.get(courseName);
        students.stream().filter(student -> student.getGradeList().stream().anyMatch(grade -> grade.getCourse().equals(course))).forEach(student -> {
            System.out.printf("学生%s选择了这门科目, \n", student.getName());
            student.getGradeList().stream().filter(grade -> grade.getCourse().equals(course)).findAny().ifPresent(ScoreManager::printGrade);
        });
    }

    public static void queryAreaCourse() {
        System.out.print("请输入课程名: ");
        String courseName = scanner.next();

        System.out.print("请输入最小总评成绩和最大总评成绩, 按空格或换行隔开: ");
        int minScore = scanner.nextInt(), maxScore = scanner.nextInt();

        System.out.printf("区间为[%d, %d)\n", minScore, maxScore);

        if (!Course.COURSE_MAP.containsKey(courseName)) {
            System.out.println("课程名不存在, 目前只有 " + Course.COURSE_MAP.keySet() + " 可供选择!");
            queryCourse();
            return;
        }

        Course course = Course.COURSE_MAP.get(courseName);
        students.stream().filter(student -> student.getGradeList().stream().filter(grade -> minScore <= grade.getSumGrade() && grade.getSumGrade() < maxScore).anyMatch(grade -> grade.getCourse().equals(course))).forEach(student -> {
            System.out.printf("学生%s的科目符合条件, \n", student.getName());
            student.getGradeList().stream().filter(grade -> grade.getCourse().equals(course)).findAny().ifPresent(ScoreManager::printGrade);
        });
    }

    public static void main(String[] args) {
        if (students.isEmpty())
            inputStudents();

        System.out.println("请输入您需要的功能: 1 - 按学生名查询学生信息, 2 - 按课程名查询选择该课程的所有学生, 3 - 分区统计某门课程的学生成绩");
        switch (scanner.nextInt()) {
            case 1:
                queryStudent();
                break;
            case 2:
                queryCourse();
                break;
            case 3:
                queryAreaCourse();
                break;
        }

        main(args);
    }
}
