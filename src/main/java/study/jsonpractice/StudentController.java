package study.jsonpractice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    @GetMapping("/student")
    public StudentDto getOneStudent() {

        Student student = new Student("홍길동", "password1234", 23,
                new ArrayList<>(Arrays.asList("수업1", "수업2", "수업3")));
        return new StudentDto(student);
    }

    @GetMapping("/students")
    public List<StudentDto> getStudents() {

        Student student1 = new Student("짱구", "password1234", 24,
                new ArrayList<>(Arrays.asList("수업1","수업2","수업3")));
        Student student2 = new Student("철수", "1234", 26,
                new ArrayList<>(Arrays.asList("수업2","수업4","수업5")));
        Student student3 = new Student("영희", "aabbcc", 20,
                new ArrayList<>(Arrays.asList("수업4","수업6")));

        ArrayList<StudentDto> students = new ArrayList<>();

        students.add(new StudentDto(student1));
        students.add(new StudentDto(student2));
        students.add(new StudentDto(student3));

        return students;
    }

    @PostMapping("/student")
    public String postStudent(@RequestBody Student student) {
        String result = String.format("[학생 정보]\n" + "학생 이름 : %s\n나이 : %d\n수업 : %s",
                student.getName(), student.getAge(),student.getClasses());
        return result;
    }

    @PostMapping("/students")
    public String postStudents(@RequestBody List<Student> studentList) {
        String result = "[학생 정보]\n";
        int index = 1;
        for (Student student : studentList) {
            result += String.format("%d번 학생\n", index++);
            result += String.format("학생 이름 : %s\n나이 : %d\n수업 : %s\n\n",
                    student.getName(), student.getAge(),student.getClasses());

        }

        return result;
    }


}
